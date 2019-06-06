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

import dali.model.Response;
import dali.service.api.ResponseService;
import io.springlets.web.NotFoundException;

@JsonComponent
/**
 * = ResponseDeserializer
 TODO Auto-generated class documentation
 *
 */
public class ResponseDeserializer extends JsonObjectDeserializer<Response> {

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ResponseService responseService;

    /**
     * TODO Auto-generated attribute documentation
     *
     */
    private ConversionService conversionService;

    /**
     * TODO Auto-generated constructor documentation
     *
     * @param responseService
     * @param conversionService
     */
    @Autowired
    public ResponseDeserializer(@Lazy ResponseService responseService, ConversionService conversionService) {
        this.responseService = responseService;
        this.conversionService = conversionService;
    }

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
     * @return Response
     * @throws IOException
     */
    @Override
	public Response deserializeObject(JsonParser jsonParser, DeserializationContext context, ObjectCodec codec, JsonNode tree) throws IOException {
        String idText = tree.asText();
        String id = conversionService.convert(idText, String.class);
        Response response = responseService.findOne(id);
        if (response == null) {
            throw new NotFoundException("Response not found");
        }
        return response;
    }
}
