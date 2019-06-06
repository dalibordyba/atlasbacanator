package dali.cache;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.HTreeMap.KeySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import dali.model.Bacon;

/**
 * Setup queues and schedule their cache loaders
 * 
 * @author Dalibor Dyba
 *
 */
@Configuration
@EnableScheduling
public class BaconCacheManager implements SchedulingConfigurer {

	final int DEFAULT_NUMBER_OF_CACHES = 2;
	final long DEFAULT_PERSIST_RATE = 6L;
	final String DEFAULT_CRON = "5 * * * * MON-FRI";

	@Value("${cache.count}")
	int nrOfCaches = DEFAULT_NUMBER_OF_CACHES;

	@Value("${cron.scheduling.cache}")
	String cron = DEFAULT_CRON;

	@Value("${cache.persist.rate}")
	Long persistRate = DEFAULT_PERSIST_RATE;

	Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	Map<String, BaconCacheExecutor> scheduledExecutors = new HashMap<>();

	Map<String, ScheduledFuture> scheduledCaches = new HashMap<>();

	Map<String, List<Bacon>> caches = new HashMap<>(); // to know which caches were setup

	Map<String, DB> cachesDb = new HashMap<>();
	

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.initialize();

		taskRegistrar.setScheduler(scheduler);

		for (int i = 0; i < nrOfCaches; i++) {
			String file = "baconQueue" + i;
			DB db = DBMaker.fileDB(file).closeOnJvmShutdown().make();
			cachesDb.put(file, db);
			KeySet<?> baconSet = db.hashSet(file).createOrOpen();

			List<Bacon> baconQueue = (List<Bacon>) Collections.synchronizedList(new ArrayList<>(baconSet));
			caches.put(file, baconQueue);

			BaconCacheLoader baconCacheLoader = new BaconCacheLoader(file);
			baconCacheLoader.registerQueue(baconQueue);

			BaconCacheExecutor baconCacheExecutor = new BaconCacheExecutor();
			baconCacheExecutor.registerBaconCacheLoader(baconCacheLoader);

			scheduledExecutors.putIfAbsent(file, baconCacheExecutor);

			ScheduledFuture future = taskRegistrar.getScheduler().schedule(baconCacheExecutor, new CronTrigger(cron));
			scheduledCaches.putIfAbsent(file, future);

			logger.info("schedule cache  {}  to execute at {} ", file, cron);
		}
		
		// Setup persisting thread
		ScheduledExecutorService persistingThread = Executors.newScheduledThreadPool(1);

		persistingThread.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				Collection<ScheduledFuture> scheduling = getScheduledCaches().values();

				for (ScheduledFuture cacheScheduling : scheduling) {
					if (!cacheScheduling.isDone()) {
						cacheScheduling.cancel(true);
					}
				}

				for (Entry<String, DB> dbEntry : cachesDb.entrySet()) {
					String cache = dbEntry.getKey();
					logger.info("Persisting cache {} ", cache);
					dbEntry.getValue().commit();
					ScheduledFuture future = taskRegistrar.getScheduler().schedule(scheduledExecutors.get(cache),
							new CronTrigger(cron));
					logger.info("schedule cache  {}  to execute at {} ", cache, cron);
					scheduledCaches.put(dbEntry.getKey(), future);
				}

			}
		}, persistRate, persistRate, TimeUnit.MINUTES);
	}

	public void cancelCacheLoaderScheduling(String cache) {
		ScheduledFuture future = scheduledCaches.get(cache);
		if (future != null) {
			future.cancel(false);
			logger.info("cancel scheduling for cache {} ", cache);
		}
	}

	public Map<String, List<Bacon>> getCaches() {
		return this.caches;
	}

	public Map<String, ScheduledFuture> getScheduledCaches() {
		return scheduledCaches;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public void setCaches(Map<String, List<Bacon>> caches) {
		this.caches = caches;
	}

	

}
