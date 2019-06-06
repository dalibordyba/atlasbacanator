package dali.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dali.model.Bacon;
import dali.model.Response;
import dali.repository.BaconRepository;
import dali.service.api.BaconService;
import io.springlets.data.domain.GlobalSearch;

@Service
@Transactional(readOnly = true)
/**
 * = BaconServiceImpl
 TODO Auto-generated class documentation
 *
 */
public class BaconServiceImpl implements BaconService {

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private BaconRepository baconRepository;

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param baconRepository
     */
    @Autowired
    public BaconServiceImpl(BaconRepository baconRepository) {
        setBaconRepository(baconRepository);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return BaconRepository
     */
    public BaconRepository getBaconRepository() {
        return baconRepository;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param baconRepository
     */
    public void setBaconRepository(BaconRepository baconRepository) {
        this.baconRepository = baconRepository;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param bacon
     */
    @Override
	@Transactional
    public void delete(Bacon bacon) {
        // Clear bidirectional many-to-one child relationship with Response
        if (bacon.getResponse() != null) {
            bacon.getResponse().getItems().remove(bacon);
        }
        
        getBaconRepository().delete(bacon);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param entities
     * @return List
     */
    @Override
	@Transactional
    public List<Bacon> save(Iterable<Bacon> entities) {
        return getBaconRepository().save(entities);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     */
    @Override
	@Transactional
    public void delete(Iterable<String> ids) {
        List<Bacon> toDelete = getBaconRepository().findAll(ids);
        getBaconRepository().deleteInBatch(toDelete);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Bacon
     */
    @Override
	@Transactional
    public Bacon save(Bacon entity) {
        return getBaconRepository().save(entity);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Bacon
     */
    @Override
	public Bacon findOne(String id) {
        return getBaconRepository().findOne(id);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Bacon
     */
    @Override
	public Bacon findOneForUpdate(String id) {
        return getBaconRepository().findOneDetached(id);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @return List
     */
    @Override
	public List<Bacon> findAll(Iterable<String> ids) {
        return getBaconRepository().findAll(ids);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return List
     */
    @Override
	public List<Bacon> findAll() {
        return getBaconRepository().findAll();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Long
     */
    @Override
	public long count() {
        return getBaconRepository().count();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    @Override
	public Page<Bacon> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getBaconRepository().findAll(globalSearch, pageable);
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
        return getBaconRepository().findAllByIdsIn(ids, globalSearch, pageable);
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
        return getBaconRepository().findByResponse(response, globalSearch, pageable);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param response
     * @return Long
     */
    @Override
	public long countByResponse(Response response) {
        return getBaconRepository().countByResponse(response);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Class
     */
    @Override
	public Class<Bacon> getEntityType() {
        return Bacon.class;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Class
     */
    @Override
	public Class<String> getIdType() {
        return String.class;
    }
}
