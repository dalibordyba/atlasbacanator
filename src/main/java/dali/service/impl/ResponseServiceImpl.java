package dali.service.impl;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dali.cache.BaconCacheManager;
import dali.model.Bacon;
import dali.model.Histogram;
import dali.model.Response;
import dali.repository.ResponseRepository;
import dali.service.api.BaconService;
import dali.service.api.HistogramService;
import dali.service.api.ResponseService;
import io.springlets.data.domain.GlobalSearch;

@Service
@Transactional(readOnly = false)
/**
 * = ResponseServiceImpl
 TODO Auto-generated class documentation
 *
 */
public class ResponseServiceImpl implements ResponseService {

	private static final int WAIT_FOR_BACONS = 10000;
	
	@Autowired
	BaconCacheManager baconCacheManager;

	@Autowired
	HistogramService histogramService;
	
	
	@Override
	public List<Response> getBacons(Integer requested) {
		Map<String, List<Bacon>> caches = getBaconCacheManager().getCaches();

		Instant start = Instant.now();
		

		Response response = createResponse(start);
		
		List<Bacon> bacons = new ArrayList<>();
		
		while (bacons.size() < requested) {
			bacons.addAll(createBacons(requested, caches));

			if (bacons.size() < requested) {
				try {
					Thread.sleep(WAIT_FOR_BACONS);
				} catch (InterruptedException | IllegalArgumentException e) {
					//continue with work early
				}
			}
		}
		
		bacons.stream().parallel().forEach((b->b.setResponse(response)));
		response.setItems(bacons);
		
		List<Map<String,Integer>> baconWordFrequency  = bacons.stream().parallel().map(b -> histogram.apply(b)).collect(Collectors.toList());
		
		List<Histogram> histograms = new ArrayList<>();
		for (Map<String, Integer> map : baconWordFrequency) {
			// convert into histogram records
			for (Entry<String, Integer> item : map.entrySet()) {
				Histogram histogram = new Histogram();
				histogram.setWord(item.getKey());
				histogram.setFrequency(item.getValue());

				histograms.add(histogram);
			}

			if (histograms.size() > 0) {
				histogramService.save(histograms);
				histograms.clear();
			}
		}
		
		List<Response> responseList = new ArrayList<>();
		responseList.add(response);
		
		Instant end = Instant.now();
		
		Date endDate = Date.from(end);
		response.setEnd(endDate);

		response.setDuration(Duration.between(start, end).toMillis()); 
		
		return responseList;

	}

	/**
	 * Create requested number of bacons or return all bacons from caches
	 * 
	 * @param requested
	 * @param caches
	 * @return requested number of bacons or  all bacons from all caches
	 */
	private List<Bacon> createBacons(Integer requested, Map<String, List<Bacon>> caches) {
		List<Bacon> result = new ArrayList<>();
		for (List<Bacon> bacons : caches.values()) {
			if (result.size() == requested) {
				return result;
			}
			if (bacons.size() > requested) {
				result.addAll(bacons.subList(0, requested));
				bacons.removeAll(result);
				break;
			} else {
				result.addAll(bacons);
				bacons.clear();
			}
		}
		return result;
	}
	
	private Response createResponse(Instant start) {
    	Response response = new Response();
    	
		Date startDate = Date.from(start);
		response.setStart(startDate);

		response.setId(RandomStringUtils.randomAlphabetic(11));
		
		
		return response;
		
		
	}
	
	Function<Bacon, Map<String,Integer>> histogram = new Function<Bacon, Map<String,Integer>>(){

		@Override
		public Map<String,Integer> apply(Bacon bacon) {
			Map<String, Integer> result = new HashMap<>();
			String[] words = bacon.getData().split("\\s+");
			for (int i = 0; i < words.length; i++) {
				String word = words[i];
				
				if(word.length() > 2) {
					result.computeIfPresent(word, (k,v) -> v+1);
					result.putIfAbsent(word, 1);
				}
			}
			return result;
		};

	};



	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private ResponseRepository responseRepository;

	/**
     * TODO Auto-generated attribute documentation
     * 
     */
    private BaconService baconService;

	/**
     * TODO Auto-generated constructor documentation
     * 
     * @param responseRepository
     * @param baconService
     */
    @Autowired
    public ResponseServiceImpl(ResponseRepository responseRepository, @Lazy BaconService baconService) {
        setResponseRepository(responseRepository);
        setBaconService(baconService);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return ResponseRepository
     */
    public ResponseRepository getResponseRepository() {
        return responseRepository;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param responseRepository
     */
    public void setResponseRepository(ResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return BaconService
     */
    public BaconService getBaconService() {
        return baconService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param baconService
     */
    public void setBaconService(BaconService baconService) {
        this.baconService = baconService;
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param response
     * @param itemsToAdd
     * @return Response
     */
    @Override
	@Transactional
    public Response addToItems(Response response, Iterable<String> itemsToAdd) {
        List<Bacon> items = getBaconService().findAll(itemsToAdd);
        response.addToItems(items);
        return getResponseRepository().save(response);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param response
     * @param itemsToRemove
     * @return Response
     */
    @Override
	@Transactional
    public Response removeFromItems(Response response, Iterable<String> itemsToRemove) {
        List<Bacon> items = getBaconService().findAll(itemsToRemove);
        response.removeFromItems(items);
        return getResponseRepository().save(response);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param response
     * @param items
     * @return Response
     */
    @Override
	@Transactional
    public Response setItems(Response response, Iterable<String> items) {
        List<Bacon> bacons = getBaconService().findAll(items);
        List<Bacon> currents = response.getItems();
        Set<Bacon> toRemove = new HashSet<Bacon>();
        for (Iterator<Bacon> iterator = currents.iterator(); iterator.hasNext();) {
            Bacon nextBacon = iterator.next();
            if (bacons.contains(nextBacon)) {
                bacons.remove(nextBacon);
            } else {
                toRemove.add(nextBacon);
            }
        }
        response.removeFromItems(toRemove);
        response.addToItems(bacons);
        // Force the version update of the parent side to know that the parent has changed
        // because it has new books assigned
        response.setVersion(response.getVersion() + 1);
        return getResponseRepository().save(response);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param response
     */
    @Override
	@Transactional
    public void delete(Response response) {
        // Clear bidirectional one-to-many parent relationship with Bacon
        for (Bacon item : response.getItems()) {
            item.setResponse(null);
        }
        
        getResponseRepository().delete(response);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param entities
     * @return List
     */
    @Override
	@Transactional
    public List<Response> save(Iterable<Response> entities) {
        return getResponseRepository().save(entities);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     */
    @Override
	@Transactional
    public void delete(Iterable<String> ids) {
        List<Response> toDelete = getResponseRepository().findAll(ids);
        getResponseRepository().deleteInBatch(toDelete);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param entity
     * @return Response
     */
    @Override
	@Transactional
    public Response save(Response entity) {
        return getResponseRepository().save(entity);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Response
     */
    @Override
	public Response findOne(String id) {
        return getResponseRepository().findOne(id);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param id
     * @return Response
     */
    @Override
	public Response findOneForUpdate(String id) {
        return getResponseRepository().findOneDetached(id);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param ids
     * @return List
     */
    @Override
	public List<Response> findAll(Iterable<String> ids) {
        return getResponseRepository().findAll(ids);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return List
     */
    @Override
	public List<Response> findAll() {
        return getResponseRepository().findAll();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Long
     */
    @Override
	public long count() {
        return getResponseRepository().count();
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @param globalSearch
     * @param pageable
     * @return Page
     */
    @Override
	public Page<Response> findAll(GlobalSearch globalSearch, Pageable pageable) {
        return getResponseRepository().findAll(globalSearch, pageable);
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
        return getResponseRepository().findAllByIdsIn(ids, globalSearch, pageable);
    }

	/**
     * TODO Auto-generated method documentation
     * 
     * @return Class
     */
    @Override
	public Class<Response> getEntityType() {
        return Response.class;
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

	public BaconCacheManager getBaconCacheManager() {
		return baconCacheManager;
	}
}
