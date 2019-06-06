package dali.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;

import dali.model.Histogram;
import dali.model.QHistogram;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;

@Transactional
/**
 * = HistogramRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */ 
public class HistogramRepositoryImpl extends QueryDslRepositorySupportExt<Histogram> implements HistogramRepositoryCustom {

    /**
     * TODO Auto-generated constructor documentation
     */
    HistogramRepositoryImpl() {
        super(Histogram.class);
    }

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String WORD = "word";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String FREQUENCY = "frequency";

	/**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    @Override
	public Page<Histogram> findAll(GlobalSearch globalSearch, Pageable pageable) {
        
        QHistogram histogram = QHistogram.histogram;
        
        JPQLQuery<Histogram> query = from(histogram);
        
        Path<?>[] paths = new Path<?>[] {histogram.word,histogram.frequency};        
        applyGlobalSearch(globalSearch, query, paths);
        
        AttributeMappingBuilder mapping = buildMapper()
			.map(WORD, histogram.word)
			.map(FREQUENCY, histogram.frequency);
        
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        
        return loadPage(query, pageable, histogram);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    @Override
	public Page<Histogram> findAllByIdsIn(List<Long> ids, GlobalSearch globalSearch, Pageable pageable) {
        
        QHistogram histogram = QHistogram.histogram;
        
        JPQLQuery<Histogram> query = from(histogram);
        
        Path<?>[] paths = new Path<?>[] {histogram.word,histogram.frequency};        
        applyGlobalSearch(globalSearch, query, paths);
        
        // Also, filter by the provided ids
        query.where(histogram.id.in(ids));
        
        AttributeMappingBuilder mapping = buildMapper()
			.map(WORD, histogram.word)
			.map(FREQUENCY, histogram.frequency);
        
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        
        return loadPage(query, pageable, histogram);
    }
}