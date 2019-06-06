package dali.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dali.model.Bacon;
import dali.model.Response;
import io.springlets.data.domain.GlobalSearch;

/**
 * = BaconRepositoryCustom
 TODO Auto-generated class documentation
 *
 */
public interface BaconRepositoryCustom {

	/**
     * TODO Auto-generated method documentation
     * 
     * @param response
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Bacon> findByResponse(Response response, GlobalSearch globalSearch, Pageable pageable);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Bacon> findAll(GlobalSearch globalSearch, Pageable pageable);


	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    public abstract Page<Bacon> findAllByIdsIn(List<String> ids, GlobalSearch globalSearch, Pageable pageable);

}
