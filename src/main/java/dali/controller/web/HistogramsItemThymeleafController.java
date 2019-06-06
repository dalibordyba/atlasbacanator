package dali.controller.web;
import java.util.Locale;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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

import dali.model.Histogram;
import dali.service.api.HistogramService;
import io.springlets.web.NotFoundException;
import io.springlets.web.mvc.util.ControllerMethodLinkBuilderFactory;
import io.springlets.web.mvc.util.MethodLinkBuilderFactory;

@Controller
@RequestMapping(value = "/histograms/{histogram}",name = "HistogramsItemThymeleafController",produces = MediaType.TEXT_HTML_VALUE)
/**
 * = HistogramsItemThymeleafController
 TODO Auto-generated class documentation
 *
 */
public class HistogramsItemThymeleafController {

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
     * TODO Auto-generated attribute documentation
     * 
     */
    private MessageSource messageSource;

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private MethodLinkBuilderFactory<HistogramsItemThymeleafController> itemLink;

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private MethodLinkBuilderFactory<HistogramsCollectionThymeleafController> collectionLink;

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param histogramService
     * @param messageSource
     * @param linkBuilder
     */
    @Autowired
    public HistogramsItemThymeleafController(HistogramService histogramService, MessageSource messageSource, ControllerMethodLinkBuilderFactory linkBuilder) {
        setHistogramService(histogramService);
        setMessageSource(messageSource);
        setItemLink(linkBuilder.of(HistogramsItemThymeleafController.class));
        setCollectionLink(linkBuilder.of(HistogramsCollectionThymeleafController.class));
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
    public MethodLinkBuilderFactory<HistogramsItemThymeleafController> getItemLink() {
        return itemLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param itemLink
     */
    public void setItemLink(MethodLinkBuilderFactory<HistogramsItemThymeleafController> itemLink) {
        this.itemLink = itemLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return MethodLinkBuilderFactory
     */
    public MethodLinkBuilderFactory<HistogramsCollectionThymeleafController> getCollectionLink() {
        return collectionLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param collectionLink
     */
    public void setCollectionLink(MethodLinkBuilderFactory<HistogramsCollectionThymeleafController> collectionLink) {
        this.collectionLink = collectionLink;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @param locale
     * @param method
     * @return Histogram
     */
    @ModelAttribute
    public Histogram getHistogram(@PathVariable("histogram") Long id, Locale locale, HttpMethod method) {
        Histogram histogram = null;
        if (HttpMethod.PUT.equals(method)) {
            histogram = histogramService.findOneForUpdate(id);
        } else {
            histogram = histogramService.findOne(id);
        }
        
        if (histogram == null) {
            String message = messageSource.getMessage("error_NotFound", new Object[] {"Histogram", id}, "The record couldn't be found", locale);
            throw new NotFoundException(message);
        }
        return histogram;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param histogram
     * @param model
     * @return ModelAndView
     */
    @GetMapping(name = "show")
    public ModelAndView show(@ModelAttribute Histogram histogram, Model model) {
        model.addAttribute("histogram", histogram);
        return new ModelAndView("histograms/show");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param histogram
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/inline", name = "showInline")
    public ModelAndView showInline(@ModelAttribute Histogram histogram, Model model) {
        model.addAttribute("histogram", histogram);
        return new ModelAndView("histograms/showInline :: inline-content");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param dataBinder
     */
    @InitBinder("histogram")
    public void initHistogramBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param model
     */
    public void populateFormats(Model model) {
        model.addAttribute("application_locale", LocaleContextHolder.getLocale().getLanguage());
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
     * @param histogram
     * @param model
     * @return ModelAndView
     */
    @GetMapping(value = "/edit-form", name = "editForm")
    public ModelAndView editForm(@ModelAttribute Histogram histogram, Model model) {
        populateForm(model);
        
        model.addAttribute("histogram", histogram);
        return new ModelAndView("histograms/edit");
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param histogram
     * @param result
     * @param version
     * @param concurrencyControl
     * @param model
     * @return ModelAndView
     */
    @PutMapping(name = "update")
    public ModelAndView update(@Valid @ModelAttribute Histogram histogram, BindingResult result, @RequestParam("version") Integer version, @RequestParam(value = "concurrency", required = false, defaultValue = "") String concurrencyControl, Model model) {
        // Check if provided form contain errors
        if (result.hasErrors()) {
            populateForm(model);
            
            return new ModelAndView("histograms/edit");
        }
        // Concurrency control
        Histogram existingHistogram = getHistogramService().findOne(histogram.getId());
        if(histogram.getVersion() != existingHistogram.getVersion() && StringUtils.isEmpty(concurrencyControl)){
            populateForm(model);
            model.addAttribute("histogram", histogram);
            model.addAttribute("concurrency", true);
            return new ModelAndView("histograms/edit");
        } else if(histogram.getVersion() != existingHistogram.getVersion() && "discard".equals(concurrencyControl)){
            populateForm(model);
            model.addAttribute("histogram", existingHistogram);
            model.addAttribute("concurrency", false);
            return new ModelAndView("histograms/edit");
        } else if(histogram.getVersion() != existingHistogram.getVersion() && "apply".equals(concurrencyControl)){
            // Update the version field to be able to override the existing values
            histogram.setVersion(existingHistogram.getVersion());
        }
        Histogram savedHistogram = getHistogramService().save(histogram);
        UriComponents showURI = getItemLink().to(HistogramsItemThymeleafLinkFactory.SHOW).with("histogram", savedHistogram.getId()).toUri();
        return new ModelAndView("redirect:" + showURI.toUriString());
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param histogram
     * @return ResponseEntity
     */
    @ResponseBody
    @DeleteMapping(name = "delete")
    public ResponseEntity<?> delete(@ModelAttribute Histogram histogram) {
        getHistogramService().delete(histogram);
        return ResponseEntity.ok().build();
    }
}
