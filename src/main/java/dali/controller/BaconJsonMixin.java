package dali.controller;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import dali.model.Response;

/**
 * = BaconJsonMixin
 TODO Auto-generated class documentation
 *
 */
public abstract class BaconJsonMixin {

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    @JsonDeserialize(using = ResponseDeserializer.class)
    private Response response;

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Response
     */
    public Response getResponse() {
        return response;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param response
     */
    public void setResponse(Response response) {
        this.response = response;
    }
}
