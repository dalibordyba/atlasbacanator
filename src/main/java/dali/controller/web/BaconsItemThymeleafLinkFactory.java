package dali.controller.web;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import io.springlets.web.mvc.util.MethodLinkFactory;
import io.springlets.web.mvc.util.SpringletsMvcUriComponentsBuilder;

@Component
/**
 * = BaconsItemThymeleafLinkFactory
 TODO Auto-generated class documentation
 *
 */
public class BaconsItemThymeleafLinkFactory implements MethodLinkFactory<BaconsItemThymeleafController> {

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String SHOW = "show";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String SHOWINLINE = "showInline";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String EDITFORM = "editForm";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String UPDATE = "update";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String DELETE = "delete";

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Class
     */
    @Override
	public Class<BaconsItemThymeleafController> getControllerClass() {
        return BaconsItemThymeleafController.class;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param methodName
     * @param parameters
     * @param pathVariables
     * @return UriComponents
     */
    @Override
	public UriComponents toUri(String methodName, Object[] parameters, Map<String, Object> pathVariables) {
        if (methodName.equals(SHOW)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).show(null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(SHOWINLINE)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).showInline(null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(EDITFORM)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).editForm(null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(UPDATE)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).update(null, null, null, null, null)).buildAndExpand(pathVariables);
        }
        if (methodName.equals(DELETE)) {
            return SpringletsMvcUriComponentsBuilder.fromMethodCall(SpringletsMvcUriComponentsBuilder.on(getControllerClass()).delete(null)).buildAndExpand(pathVariables);
        }
        throw new IllegalArgumentException("Invalid method name: " + methodName);
    }
}
