package dali.service.api;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dali.model.Response;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.format.EntityResolver;

/**
 * = ResponseService
 TODO Auto-generated class documentation
 *
 */
public interface ResponseService extends EntityResolver<Response, String> {

	/**
	 * Getting requested number of bacons through all configured caches
	 * If there is not enough bacons its waiting for a while and try it again
	 * 
	 * @param requested
	 * @return response list with one response including requested number of bacons
	 */
	List<Response> getBacons(Integer requested);

	@Override
	public abstract Response findOne(String id);

    public abstract void delete(Response response);

    public abstract List<Response> save(Iterable<Response> entities);

    public abstract void delete(Iterable<String> ids);


    public abstract Response save(Response entity);

    public abstract Response findOneForUpdate(String id);

    public abstract List<Response> findAll(Iterable<String> ids);

    public abstract List<Response> findAll();

    public abstract long count();

    public abstract Page<Response> findAll(GlobalSearch globalSearch, Pageable pageable);

    public abstract Page<Response> findAllByIdsIn(List<String> ids, GlobalSearch globalSearch, Pageable pageable);

    public abstract Response addToItems(Response response, Iterable<String> itemsToAdd);

    public abstract Response removeFromItems(Response response, Iterable<String> itemsToRemove);

    public abstract Response setItems(Response response, Iterable<String> items);

}
