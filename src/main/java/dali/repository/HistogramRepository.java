package dali.repository;
import org.springframework.transaction.annotation.Transactional;

import dali.model.Histogram;
import io.springlets.data.jpa.repository.DetachableJpaRepository;

@Transactional
/**
 * = HistogramRepository
 TODO Auto-generated class documentation
 *
 */
public interface HistogramRepository extends DetachableJpaRepository<Histogram, Long>, HistogramRepositoryCustom {
}
