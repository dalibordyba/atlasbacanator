package dali.controller.web;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dali.model.Bacon;

/**
 * = ResponseJsonMixin
 TODO Auto-generated class documentation
 *
 */
public abstract class ResponseJsonMixin {

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    @JsonIgnore
    private List<Bacon> items;

	/**
     * TODO Auto-generated method documentation
     * 
     * @return List
     */
    public List<Bacon> getItems() {
        return items;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param items
     */
    public void setItems(List<Bacon> items) {
        this.items = items;
    }
}
