package dali.service.api;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dali.model.Histogram;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.format.EntityResolver;

/**
 * = HistogramService
 TODO Auto-generated class documentation
 *
 */
public interface HistogramService extends EntityResolver<Histogram, Long> {

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Histogram
     */
    @Override
	public abstract Histogram findOne(Long id);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param histogram
     */
    public abstract void delete(Histogram histogram);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param entities
     * @return List
     */
    public abstract List<Histogram> save(Iterable<Histogram> entities);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     */
    public abstract void delete(Iterable<Long> ids);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Histogram
     */
    public abstract Histogram save(Histogram entity);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Histogram
     */
    public abstract Histogram findOneForUpdate(Long id);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @return List
     */
    public abstract List<Histogram> findAll(Iterable<Long> ids);


	/**
     * TODO Auto-generated method documentation
     * 
     * @return List
     */
    public abstract List<Histogram> findAll();


	/**
     * TODO Auto-generated method documentation
     * 
     * @return Long
     */
    public abstract long count();


	/**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Histogram> findAll(GlobalSearch globalSearch, Pageable pageable);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Histogram> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable);

}
