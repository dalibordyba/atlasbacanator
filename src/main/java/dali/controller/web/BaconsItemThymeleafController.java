package dali.controller.web;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;

import dali.model.Bacon;
import dali.service.api.BaconService;
import io.springlets.web.NotFoundException;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;

@Controller
@RequestMapping(value = "/bacons/{bacon}",name = "BaconsItemThymeleafController",produces = MediaType.TEXT_HTML_VALUE)
/**
 * = BaconsItemThymeleafController
 TODO Auto-generated class documentation
 *
 */
public class BaconsItemThymeleafController {

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private MessageSource messageSource;

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private MethodLinkBuilderFactory<BaconsItemThymeleafController> itemLink;

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private MethodLinkBuilderFactory<BaconsCollectionThymeleafController> collectionLink;

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param baconService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public BaconsItemThymeleafController(BaconService baconService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setBaconService(baconService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(BaconsItemThymeleafController.class));
        setCollectionLink(linkBuilder.of(BaconsCollectionThymeleafController.class));
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return MessageSource
     */
    public MessageSource getMessageSource() {
        return messageSource;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param messageSource
     */
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<BaconsItemThymeleafController> getItemLink() {
        return itemLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<BaconsItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<BaconsCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<BaconsCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @param locale
     * @param method
     * @return Bacon
     */
    @ModelAttribute
    public Bacon getBacon(@PathVariable("bacon") String id, Locale locale, HttpMethod method) {
        Bacon bacon = null;
        if (HttpMethod.PUT.equals(method)) {
            bacon = baconService.findOneForUpdate(id);
        } else {
            bacon = baconService.findOne(id);
        }
        
        if (bacon == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] {"Bacon", id}, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return bacon;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param bacon
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute Bacon bacon, Model model) {
        model.addAttribute("bacon", bacon);
        return new ModelAndView("bacons/show");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param bacon
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute Bacon bacon, Model model) {
        model.addAttribute("bacon", bacon);
        return new ModelAndView("bacons/showInline :: inline-content");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param dataBinder
     */
    @InitBinder("bacon")
    public void initBaconBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param model
     */
    public void populateFormats(Model model) {
        model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
        model.addAttribute("start_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        model.addAttribute("end_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param model
     */
    public void populateForm(Model model) {
        populateFormats(model);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param bacon
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute Bacon bacon, Model model) {
        populateForm(model);
        
        model.addAttribute("bacon", bacon);
        return new ModelAndView("bacons/edit");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param bacon
     * @param result
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute Bacon bacon, BindingResult result, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);
            
            return new ModelAndView("bacons/edit");
        }
        // Concurrency control
        Bacon existingBacon = getBaconService().findOne(bacon.getId());
        if(bacon.getVersion() != existingBacon.getVersion() && StringUtils.isEmpty(concurrencyControl)){
            populateForm(model);
            model.addAttribute("bacon", bacon);
            model.addAttribute("concurrency", true);
            return new ModelAndView("bacons/edit");
        } else if(bacon.getVersion() != existingBacon.getVersion() && "discard".equals(concurrencyControl)){
            populateForm(model);
            model.addAttribute("bacon", existingBacon);
            model.addAttribute("concurrency", false);
            return new ModelAndView("bacons/edit");
        } else if(bacon.getVersion() != existingBacon.getVersion() && "apply".equals(concurrencyControl)){
            // Update the version field to be able to override the existing values
            bacon.setVersion(existingBacon.getVersion());
        }
        Bacon savedBacon = getBaconService().save(bacon);
        UriComponents showURI = getItemLink().to(BaconsItemThymeleafLinkFactory.SHOW).with("bacon", savedBacon.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param bacon
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Bacon bacon) {
        getBaconService().delete(bacon);
        return ResponseEntity.ok().build();
    }

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
}
