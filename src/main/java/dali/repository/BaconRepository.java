package dali.repository;
import org.springframework.transaction.annotation.Transactional;

import dali.model.Bacon;
import dali.model.Response;
import io.springlets.data.jpa.repository.DetachableJpaRepository;

@Transactional(readOnly = true)
/**
 * = BaconRepository
 TODO Auto-generated class documentation
 *
 */
public interface BaconRepository extends DetachableJpaRepository<Bacon, String>, BaconRepositoryCustom {

	/**
     * TODO Auto-generated method documentation
     * 
     * @param response
     * @return Long
     */
    public abstract long countByResponse(Response response);

}
