package dali.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dali.model.Response;
import io.springlets.data.domain.GlobalSearch;

/**
 * = ResponseRepositoryCustom
 TODO Auto-generated class documentation
 *
 */
public interface ResponseRepositoryCustom {

	/**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Response> findAll(GlobalSearch globalSearch, Pageable pageable);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Response> findAllByIdsIn(List<String> ids, GlobalSearch globalSearch, Pageable pageable);
    
}
