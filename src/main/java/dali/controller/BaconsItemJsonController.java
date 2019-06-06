package dali.controller;
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

import dali.model.Bacon;
import dali.service.api.BaconService;
import io.springlets.web.NotFoundException;

@RestController
@RequestMapping(value = "/bacons/{bacon}",name = "BaconsItemJsonController",produces = MediaType.APPLICATION_JSON_VALUE)
/**
 * = BaconsItemJsonController
 TODO Auto-generated class documentation
 *
 */
public class BaconsItemJsonController {

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
    public BaconsItemJsonController(BaconService baconService) {
        this.baconService = baconService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Bacon
     */
    @ModelAttribute
    public Bacon getBacon(@PathVariable("bacon") String id) {
        Bacon bacon = baconService.findOne(id);
        if (bacon == null) {
            throw new NotFoundException(String.format("Bacon with identifier '%s' not found",id));
        }
        return bacon;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param bacon
     * @return ResponseEntity
     */
    @GetMapping(name = "show")
    public ResponseEntity<?> show(@ModelAttribute Bacon bacon) {
        return ResponseEntity.ok(bacon);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param bacon
     * @return UriComponents
     */
    public static UriComponents showURI(Bacon bacon) {
        return MvcUriComponentsBuilder
            .fromMethodCall(
                MvcUriComponentsBuilder.on(BaconsItemJsonController.class).show(bacon))
            .buildAndExpand(bacon.getId()).encode();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param storedBacon
     * @param bacon
     * @param result
     * @return ResponseEntity
     */
    @PutMapping(name = "update")
    public ResponseEntity<?> update(@ModelAttribute Bacon storedBacon, @Valid @RequestBody Bacon bacon, BindingResult result) {
        
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
        }
        bacon.setId(storedBacon.getId());
        getBaconService().save(bacon);
        return ResponseEntity.ok().build();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param bacon
     * @return ResponseEntity
     */
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Bacon bacon) {
        getBaconService().delete(bacon);
        return ResponseEntity.ok().build();
    }
}
