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

import dali.model.Response;
import dali.service.api.ResponseService;
import io.springlets.web.NotFoundException;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;

@Controller
@RequestMapping(value = "/responses/{response}",name = "ResponsesItemThymeleafController",produces = MediaType.TEXT_HTML_VALUE)
/**
 * = ResponsesItemThymeleafController
 TODO Auto-generated class documentation
 *
 */
public class ResponsesItemThymeleafController {

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private MessageSource messageSource;

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private MethodLinkBuilderFactory<ResponsesItemThymeleafController> itemLink;

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private MethodLinkBuilderFactory<ResponsesCollectionThymeleafController> collectionLink;

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param responseService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public ResponsesItemThymeleafController(ResponseService responseService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setResponseService(responseService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(ResponsesItemThymeleafController.class));
        setCollectionLink(linkBuilder.of(ResponsesCollectionThymeleafController.class));
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
    public MethodLinkBuilderFactory<ResponsesItemThymeleafController> getItemLink() {
        return itemLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<ResponsesItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<ResponsesCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<ResponsesCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @param locale
     * @param method
     * @return Response
     */
    @ModelAttribute
    public Response getResponse(@PathVariable("response") String id, Locale locale, HttpMethod method) {
        Response response = null;
        if (HttpMethod.PUT.equals(method)) {
            response = responseService.findOneForUpdate(id);
        } else {
            response = responseService.findOne(id);
        }
        
        if (response == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] {"Response", id}, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return response;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param response
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute Response response, Model model) {
        model.addAttribute("response", response);
        return new ModelAndView("responses/show");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param response
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute Response response, Model model) {
        model.addAttribute("response", response);
        return new ModelAndView("responses/showInline :: inline-content");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param dataBinder
     */
    @InitBinder("response")
    public void initResponseBinder(WebDataBinder dataBinder) {
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
     * @param response
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute Response response, Model model) {
        populateForm(model);
        
        model.addAttribute("response", response);
        return new ModelAndView("responses/edit");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param response
     * @param result
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute Response response, BindingResult result, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);
            
            return new ModelAndView("responses/edit");
        }
        // Concurrency control
        Response existingResponse = getResponseService().findOne(response.getId());
        if(response.getVersion() != existingResponse.getVersion() && StringUtils.isEmpty(concurrencyControl)){
            populateForm(model);
            model.addAttribute("response", response);
            model.addAttribute("concurrency", true);
            return new ModelAndView("responses/edit");
        } else if(response.getVersion() != existingResponse.getVersion() && "discard".equals(concurrencyControl)){
            populateForm(model);
            model.addAttribute("response", existingResponse);
            model.addAttribute("concurrency", false);
            return new ModelAndView("responses/edit");
        } else if(response.getVersion() != existingResponse.getVersion() && "apply".equals(concurrencyControl)){
            // Update the version field to be able to override the existing values
            response.setVersion(existingResponse.getVersion());
        }
        Response savedResponse = getResponseService().save(response);
        UriComponents showURI = getItemLink().to(ResponsesItemThymeleafLinkFactory.SHOW).with("response", savedResponse.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param response
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Response response) {
        getResponseService().delete(response);
        return ResponseEntity.ok().build();
    }

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private ResponseService responseService;

	/**
     * TODO Auto-generated method documentation
     * 
     * @return ResponseService
     */
    public ResponseService getResponseService() {
        return responseService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param responseService
     */
    public void setResponseService(ResponseService responseService) {
        this.responseService = responseService;
    }
}
