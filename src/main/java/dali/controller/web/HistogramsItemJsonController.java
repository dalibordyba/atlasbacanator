package dali.controller.web;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import dali.model.Histogram;
import dali.service.api.HistogramService;
import io.springlets.web.NotFoundException;

@RestController
@RequestMapping(value = "/histograms/{histogram}",name = "HistogramsItemJsonController",produces = MediaType.APPLICATION_JSON_VALUE)
/**
 * = HistogramsItemJsonController
 TODO Auto-generated class documentation
 *
 */
public class HistogramsItemJsonController {

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param histogramService
     */
    @Autowired
    public HistogramsItemJsonController(HistogramService histogramService) {
        this.histogramService = histogramService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Histogram
     */
    @ModelAttribute
    public Histogram getHistogram(@PathVariable("histogram") Long id) {
        Histogram histogram = histogramService.findOne(id);
        if (histogram == null) {
            throw new NotFoundException(String.format("Histogram with identifier '%s' not found",id));
        }
        return histogram;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param histogram
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute Histogram histogram) {
        return ResponseEntity.ok(histogram);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param histogram
     * @return UriComponents
     */
    public static UriComponents showURI(Histogram histogram) {
        return MvcUriComponentsBuilder
            .fromMethodCall(
                MvcUriComponentsBuilder.on(HistogramsItemJsonController.class).show(histogram))
            .buildAndExpand(histogram.getId()).encode();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param storedHistogram
     * @param histogram
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute Histogram storedHistogram, @Valid @RequestBody Histogram histogram, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        histogram.setId(storedHistogram.getId());
        getHistogramService().save(histogram);
        return ResponseEntity.ok().build();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param histogram
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Histogram histogram) {
        getHistogramService().delete(histogram);
        return ResponseEntity.ok().build();
    }

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
}
