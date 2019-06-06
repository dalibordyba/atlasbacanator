package dali.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;

import dali.model.Bacon;
import dali.model.QBacon;
import dali.model.Response;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;

@Transactional(readOnly = true)
/**
 * = BaconRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */ 
public class BaconRepositoryImpl extends QueryDslRepositorySupportExt<Bacon> implements BaconRepositoryCustom {

    /**
     * TODO Auto-generated constructor documentation
     */
    BaconRepositoryImpl() {
        super(Bacon.class);
    }

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String START = "start";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String END = "end";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String DATA = "data";

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    public static final String RESPONSE = "response";

	/**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    @Override
	public Page<Bacon> findAll(GlobalSearch globalSearch, Pageable pageable) {
        
        QBacon bacon = QBacon.bacon;
        
        JPQLQuery<Bacon> query = from(bacon);
        
        Path<?>[] paths = new Path<?>[] {bacon.start,bacon.end,bacon.data,bacon.response};        
        applyGlobalSearch(globalSearch, query, paths);
        
        AttributeMappingBuilder mapping = buildMapper()
			.map(START, bacon.start)
			.map(END, bacon.end)
			.map(DATA, bacon.data)
			.map(RESPONSE, bacon.response);
        
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        
        return loadPage(query, pageable, bacon);
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
	public Page<Bacon> findAllByIdsIn(List<String> ids, GlobalSearch globalSearch, Pageable pageable) {
        
        QBacon bacon = QBacon.bacon;
        
        JPQLQuery<Bacon> query = from(bacon);
        
        Path<?>[] paths = new Path<?>[] {bacon.start,bacon.end,bacon.data,bacon.response};        
        applyGlobalSearch(globalSearch, query, paths);
        
        // Also, filter by the provided ids
        query.where(bacon.id.in(ids));
        
        AttributeMappingBuilder mapping = buildMapper()
			.map(START, bacon.start)
			.map(END, bacon.end)
			.map(DATA, bacon.data)
			.map(RESPONSE, bacon.response);
        
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        
        return loadPage(query, pageable, bacon);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param response
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    @Override
	public Page<Bacon> findByResponse(Response response, GlobalSearch globalSearch, Pageable pageable) {
        
        QBacon bacon = QBacon.bacon;
        
        JPQLQuery<Bacon> query = from(bacon);
        
        Assert.notNull(response, "response is required");
        
        query.where(bacon.response.eq(response));
        Path<?>[] paths = new Path<?>[] {bacon.start,bacon.end,bacon.data,bacon.response};        
        applyGlobalSearch(globalSearch, query, paths);
        
        AttributeMappingBuilder mapping = buildMapper()
			.map(START, bacon.start)
			.map(END, bacon.end)
			.map(DATA, bacon.data)
			.map(RESPONSE, bacon.response);
        
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        
        return loadPage(query, pageable, bacon);
    }
}