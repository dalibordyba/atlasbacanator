package dali.repository;
import org.springframework.transaction.annotation.Transactional;

import dali.model.Response;
import io.springlets.data.jpa.repository.DetachableJpaRepository;

@Transactional(readOnly = true)
/**
 * = ResponseRepository
 TODO Auto-generated class documentation
 *
 */
public interface ResponseRepository extends DetachableJpaRepository<Response, String>, ResponseRepositoryCustom {
}
