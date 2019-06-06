package dali.controller.web;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;

import dali.model.Histogram;
import dali.service.api.HistogramService;
import io.springlets.web.NotFoundException;

@JsonComponent
/**
 * = HistogramDeserializer
 TODO Auto-generated class documentation
 *
 */
public class HistogramDeserializer extends JsonObjectDeserializer<Histogram> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private HistogramService histogramService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param histogramService
     * @param conversionService
     */
    @Autowired
    public HistogramDeserializer(@Lazy HistogramService histogramService, ConversionService conversionService) {
        this.histogramService = histogramService;
        this.conversionService = conversionService;
    }

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
     * TODO Auto-generated method documentation
     * 
     * @return ConversionService
     */
    public ConversionService getConversionService() {
        return conversionService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param conversionService
     */
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param jsonParser
     * @param context
     * @param codec
     * @param tree
     * @return Histogram
     * @throws IOException
     */
    @Override
	public Histogram deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
        String idText = tree.asText();
        Long id = conversionService.convert(idText, Long.class);
        Histogram histogram = histogramService.findOne(id);
        if (histogram == null) {
            throw new NotFoundException("Histogram not found");
        }
        return histogram;
    }
}
