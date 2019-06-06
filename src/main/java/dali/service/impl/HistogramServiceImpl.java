package dali.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dali.model.Histogram;
import dali.repository.HistogramRepository;
import dali.service.api.HistogramService;
import io.springlets.data.domain.GlobalSearch;

@Service
/**
 * = HistogramServiceImpl
 TODO Auto-generated class documentation
 *
 */
public class HistogramServiceImpl implements HistogramService {

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private HistogramRepository histogramRepository;

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param histogramRepository
     */
    @Autowired
    public HistogramServiceImpl(HistogramRepository histogramRepository) {
        setHistogramRepository(histogramRepository);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return HistogramRepository
     */
    public HistogramRepository getHistogramRepository() {
        return histogramRepository;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param histogramRepository
     */
    public void setHistogramRepository(HistogramRepository histogramRepository) {
        this.histogramRepository = histogramRepository;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param histogram
     */
    @Override
	@Transactional
    public void delete(Histogram histogram) {
        getHistogramRepository().delete(histogram);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param entities
     * @return List
     */
    @Override
	@Transactional
    public List<Histogram> save(Iterable<Histogram> entities) {
        return getHistogramRepository().save(entities);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     */
    @Override
	@Transactional
    public void delete(Iterable<Long> ids) {
        List<Histogram> toDelete = getHistogramRepository().findAll(ids);
        getHistogramRepository().deleteInBatch(toDelete);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Histogram
     */
    @Override
	@Transactional
    public Histogram save(Histogram entity) {
        return getHistogramRepository().save(entity);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Histogram
     */
    @Override
	public Histogram findOne(Long id) {
        return getHistogramRepository().findOne(id);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Histogram
     */
    @Override
	public Histogram findOneForUpdate(Long id) {
        return getHistogramRepository().findOneDetached(id);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @return List
     */
    @Override
	public List<Histogram> findAll(Iterable<Long> ids) {
        return getHistogramRepository().findAll(ids);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return List
     */
    @Override
	public List<Histogram> findAll() {
        return getHistogramRepository().findAll();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Long
     */
    @Override
	public long count() {
        return getHistogramRepository().count();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    @Override
	public Page<Histogram> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getHistogramRepository().findAll(globalSearch, pageable);
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
        return getHistogramRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Class
     */
    @Override
	public Class<Histogram> getEntityType() {
        return Histogram.class;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Class
     */
    @Override
	public Class<Long> getIdType() {
        return Long.class;
    }
}
