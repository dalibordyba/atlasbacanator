package test.dali.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.util.Files;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap.KeySet;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import dali.cache.BaconCacheManager;
import dali.model.Bacon;
import dali.model.Response;
import dali.repository.ResponseRepositoryImpl;
import dali.service.impl.BaconServiceImpl;
import dali.service.impl.ResponseServiceImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest({BaconCacheManager.class, ResponseServiceImpl.class,BaconServiceImpl.class})
public class ResponseServiceTest {

	@InjectMocks
	BaconCacheManager baconCacheManager;
	
	@InjectMocks
	ResponseRepositoryImpl responseRepository;
	
	@InjectMocks
	BaconServiceImpl baconService;
	
	DB db;
	
	List<Bacon> baconQueue;
	
	@Mock
	ResponseServiceImpl mockResponseService;
	//ResponseService responseService = new ResponseServiceImpl((ResponseRepository) responseRepository, baconService);
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		File test = new File("test");
		if(test.exists()) {
			Files.delete(test);
		}
		
		DB db = DBMaker.fileDB(test).closeOnJvmShutdown().make();
		KeySet<?> baconSet = db.hashSet("queue").createOrOpen();
		baconQueue = (List<Bacon>) Collections.synchronizedList(new ArrayList<>(baconSet));
		
		
		
		PowerMockito.doCallRealMethod().when(mockResponseService).getBacons(any());
		when(mockResponseService.getBaconCacheManager()).thenReturn(baconCacheManager);
		//when(mockResponseService.getBacons(null)).thenCallRealMethod();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getBaconsTest() {
		
		Bacon bacon = new Bacon();
		
		Instant  start = Instant.now();
		Instant  end = Instant.now();

		Date startDate = Date.from(start);

		Date endDate = Date.from(end);


		bacon.setId(RandomStringUtils.randomAlphabetic(11));
		bacon.setData("Test lorem ipsum");
		bacon.setStart(startDate);
		bacon.setEnd(endDate);
		bacon.setDuration(Duration.between(start, end).getSeconds());
		
		baconQueue.add(bacon);
		Map<String, List<Bacon>> caches = new HashMap<>();
		caches.put("test",baconQueue);
		baconCacheManager.setCaches(caches);
		
		List responses = mockResponseService.getBacons(1);
		assertNotNull(responses);
		assertNotNull(responses.get(0));
		
		Response response = (Response) responses.get(0);
		Bacon returned = response.getItems().get(0);
		assertEquals(bacon.getId(), returned.getId());
		db.close();
		
	}

}
