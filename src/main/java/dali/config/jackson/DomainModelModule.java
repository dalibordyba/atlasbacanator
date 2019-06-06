package dali.config.jackson;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.databind.module.SimpleModule;

import dali.controller.BaconJsonMixin;
import dali.controller.web.HistogramJsonMixin;
import dali.controller.web.ResponseJsonMixin;
import dali.model.Bacon;
import dali.model.Histogram;
import dali.model.Response;

@JsonComponent
/**
 * = DomainModelModule
 TODO Auto-generated class documentation
 *
 */
public class DomainModelModule extends SimpleModule { 

	/**
     * TODO Auto-generated constructor documentation
     * 
     */
    public DomainModelModule() {
        // Mixin registration
        
        setMixInAnnotation(Bacon.class, BaconJsonMixin.class);
        setMixInAnnotation(Response.class, ResponseJsonMixin.class);
        setMixInAnnotation(Histogram.class, HistogramJsonMixin.class);

    }
}
