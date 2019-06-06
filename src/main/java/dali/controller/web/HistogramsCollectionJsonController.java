package dali.controller.web;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import dali.model.Histogram;
import dali.service.api.HistogramService;
import io.springlets.data.domain.GlobalSearch;

@RestController
@RequestMapping(value = "/histograms",name = "HistogramsCollectionJsonController",produces = MediaType.APPLICATION_JSON_VALUE)
/**
 * = HistogramsCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
public class HistogramsCollectionJsonController {

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private HistogramService histogramService;

	/**
     * TODO Auto-generated method documentation
     * 
     * @return HistogramService
     */
    public HistogramService getHistogramService() {
        return histogramService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param histogramService
     */
    public void setHistogramService(HistogramService histogramService) {
        this.histogramService = histogramService;
    }

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param histogramService
     */
    @Autowired
    public HistogramsCollectionJsonController(HistogramService histogramService) {
        this.histogramService = histogramService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<Histogram>> list(GlobalSearch globalSearch, Pageable pageable) {
        
        Page<Histogram> histograms = getHistogramService().findAll(globalSearch, pageable);
        return ResponseEntity.ok(histograms);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return UriComponents
     */
    public static UriComponents listURI() {
        return MvcUriComponentsBuilder
            .fromMethodCall(
                MvcUriComponentsBuilder.on(HistogramsCollectionJsonController.class).list(null, null))
            .build().encode();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param histogram
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody Histogram histogram, BindingResult result) {
        
        if (histogram.getId() != null || histogram.getVersion() != null) {        
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        
        Histogram newHistogram = getHistogramService().save(histogram);
        UriComponents showURI = HistogramsItemJsonController.showURI(newHistogram);
        
        return ResponseEntity.created(showURI.toUri()).build();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param histograms
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<Histogram> histograms, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        
        getHistogramService().save(histograms);
        
        return ResponseEntity.created(listURI().toUri()).build();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param histograms
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<Histogram> histograms, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        
        getHistogramService().save(histograms);
        
        return ResponseEntity.ok().build();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<Long> ids) {
        
        getHistogramService().delete(ids);
        
        return ResponseEntity.ok().build();
    }
}
