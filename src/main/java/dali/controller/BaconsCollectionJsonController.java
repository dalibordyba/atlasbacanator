package dali.controller;
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

import dali.model.Bacon;
import dali.service.api.BaconService;
import dali.service.api.ResponseService;
import io.springlets.data.domain.GlobalSearch;

@RestController
@RequestMapping(value = "/bacons",name = "BaconsCollectionJsonController",produces = MediaType.APPLICATION_JSON_VALUE)
/**
 * = BaconsCollectionJsonController
 TODO Auto-generated class documentation
 *
 */
public class BaconsCollectionJsonController {
	
	@Autowired
	ResponseService responseService;

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private BaconService baconService;

	/**
     * TODO Auto-generated method documentation
     * 
     * @return BaconService
     */
    public BaconService getBaconService() {
        return baconService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param baconService
     */
    public void setBaconService(BaconService baconService) {
        this.baconService = baconService;
    }

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param baconService
     */
    @Autowired
    public BaconsCollectionJsonController(BaconService baconService) {
        this.baconService = baconService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return ResponseEntity
     */
    @GetMapping(name = "list")
    public ResponseEntity<Page<Bacon>> list(GlobalSearch globalSearch, Pageable pageable) {
    	
    	Page<Bacon> bacons = getBaconService().findAll(globalSearch, pageable);
         return ResponseEntity.ok(bacons); 
    	
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return UriComponents
     */
    public static UriComponents listURI() {
        return MvcUriComponentsBuilder
            .fromMethodCall(
                MvcUriComponentsBuilder.on(BaconsCollectionJsonController.class).list(null, null))
            .build().encode();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param bacon
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(name = "create")
    public ResponseEntity<?> create(@Valid @RequestBody Bacon bacon, BindingResult result) {
        
        if (bacon.getId() != null || bacon.getVersion() != null) {        
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        
        Bacon newBacon = getBaconService().save(bacon);
        UriComponents showURI = BaconsItemJsonController.showURI(newBacon);
        
        return ResponseEntity.created(showURI.toUri()).build();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param bacons
     * @param result
     * @return ResponseEntity
     */
    @PostMapping(value = "/batch", name = "createBatch")
    public ResponseEntity<?> createBatch(@Valid @RequestBody Collection<Bacon> bacons, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        
        getBaconService().save(bacons);
        
        return ResponseEntity.created(listURI().toUri()).build();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param bacons
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(value = "/batch", name = "updateBatch")
    public ResponseEntity<?> updateBatch(@Valid @RequestBody Collection<Bacon> bacons, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        
        getBaconService().save(bacons);
        
        return ResponseEntity.ok().build();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @return ResponseEntity
     */
    @DeleteMapping(value = "/batch/{ids}", name = "deleteBatch")
    public ResponseEntity<?> deleteBatch(@PathVariable("ids") Collection<String> ids) {
        
        getBaconService().delete(ids);
        
        return ResponseEntity.ok().build();
    }
}
