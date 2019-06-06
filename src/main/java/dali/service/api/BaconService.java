package dali.service.api;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dali.model.Bacon;
import dali.model.Response;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.format.EntityResolver;

/**
 * = BaconService
 TODO Auto-generated class documentation
 *
 */
public interface BaconService extends EntityResolver<Bacon, String> {

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Bacon
     */
    @Override
	public abstract Bacon findOne(String id);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param bacon
     */
    public abstract void delete(Bacon bacon);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param entities
     * @return List
     */
    public abstract List<Bacon> save(Iterable<Bacon> entities);


	
    public abstract void delete(Iterable<String> ids);

    public abstract Bacon save(Bacon entity);

    public abstract Bacon findOneForUpdate(String id);

    public abstract List<Bacon> findAll(Iterable<String> ids);

    public abstract List<Bacon> findAll();

    public abstract long count();

    public abstract Page<Bacon> findAll(GlobalSearch globalSearch, Pageable pageable);

    public abstract Page<Bacon> findAllByIdsIn(List<String> ids, GlobalSearch globalSearch, Pageable pageable);

    public abstract Page<Bacon> findByResponse(Response response, GlobalSearch globalSearch, Pageable pageable);

    public abstract long countByResponse(Response response);

}
