package dali.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dali.model.Histogram;
import io.springlets.data.domain.GlobalSearch;

/**
 * = HistogramRepositoryCustom
 TODO Auto-generated class documentation
 *
 */
public interface HistogramRepositoryCustom {

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
