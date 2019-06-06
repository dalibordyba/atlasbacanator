/**
 * 
 */
package dali.cache;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import dali.model.Bacon;

/**
 * Use rest call to load data into given cache in configured interval
 * 
 * @author Dalibor Dyba
 *
 */
public class BaconCacheLoader implements Callable<String> {

	private static final String DEFAULT_BACON_URL = "https://baconipsum.com/api/?type=all-meat&paras=1";

	@Autowired
	RestTemplate restClient = new RestTemplate();

	List<Bacon> queue;

	String cacheName;

	@Value("${bacon.url}")
	String url = DEFAULT_BACON_URL;

	public BaconCacheLoader(String cacheName) {
		this.cacheName = cacheName;
	}

	@Override
	public String call() {

		Instant start = Instant.now();

		String data = restClient.getForObject(url, String.class);

		Instant end = Instant.now();
		
		Bacon bacon = createBacon(start, data, end);
		queue.add(bacon);
		return String.format("Add bacon %s into cache %s", bacon.getId(), cacheName);

	}

	public static Bacon createBacon(Instant start, String data, Instant end) {
		Bacon bacon = new Bacon();

		Date startDate = Date.from(start);

		Date endDate = Date.from(end);

		bacon.setId(RandomStringUtils.randomAlphabetic(11));
		bacon.setData(data);
		bacon.setStart(startDate);
		bacon.setEnd(endDate);
		bacon.setDuration(Duration.between(start, end).getSeconds());

		return bacon;
	}

	public void registerQueue(List<Bacon> queue) {
		this.queue = queue;
	}

}
