package dali.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Path;
import com.querydsl.jpa.JPQLQuery;

import dali.model.QResponse;
import dali.model.Response;
import io.springlets.data.domain.GlobalSearch;
import io.springlets.data.jpa.repository.support.QueryDslRepositorySupportExt;

@Transactional(readOnly = true)
/**
 * = ResponseRepositoryImpl
 *
 * TODO Auto-generated class documentation
 *
 */ 
public class ResponseRepositoryImpl extends QueryDslRepositorySupportExt<Response> implements ResponseRepositoryCustom {

    /**
     * TODO Auto-generated constructor documentation
     */
    ResponseRepositoryImpl() {
        super(Response.class);
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
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    @Override
	public Page<Response> findAll(GlobalSearch globalSearch, Pageable pageable) {
        
        QResponse response = QResponse.response;
        
        JPQLQuery<Response> query = from(response);
        
        Path<?>[] paths = new Path<?>[] {response.start,response.start};        
        applyGlobalSearch(globalSearch, query, paths);
        
        AttributeMappingBuilder mapping = buildMapper()
			.map(START, response.start)
			.map(END, response.end);
        
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        
        return loadPage(query, pageable, response);
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
	public Page<Response> findAllByIdsIn(List<String> ids, GlobalSearch globalSearch, Pageable pageable) {
        
        QResponse response = QResponse.response;
        
        JPQLQuery<Response> query = from(response);
        
        Path<?>[] paths = new Path<?>[] {response.start,response.end};        
        applyGlobalSearch(globalSearch, query, paths);
        
        // Also, filter by the provided ids
        query.where(response.id.in(ids));
        
        AttributeMappingBuilder mapping = buildMapper()
			.map(START, response.start)
			.map(END, response.end);
        
        applyPagination(pageable, query, mapping);
        applyOrderById(query);
        
        return loadPage(query, pageable, response);
    }
}