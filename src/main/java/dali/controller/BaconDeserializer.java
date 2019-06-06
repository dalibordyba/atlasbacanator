package dali.controller;
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

import dali.model.Bacon;
import dali.service.api.BaconService;
import io.springlets.web.NotFoundException;

@JsonComponent
/**
 * = BaconDeserializer
 TODO Auto-generated class documentation
 *
 */
public class BaconDeserializer extends JsonObjectDeserializer<Bacon> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private BaconService baconService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param baconService
     * @param conversionService
     */
    @Autowired
    public BaconDeserializer(@Lazy BaconService baconService, ConversionService conversionService) {
        this.baconService = baconService;
        this.conversionService = conversionService;
    }

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
     * @return Bacon
     * @throws IOException
     */
    @Override
	public Bacon deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
        String idText = tree.asText();
        String id = conversionService.convert(idText, String.class);
        Bacon bacon = baconService.findOne(id);
        if (bacon == null) {
            throw new NotFoundException("Bacon not found");
        }
        return bacon;
    }
}
