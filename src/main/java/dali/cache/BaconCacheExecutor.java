package dali.cache;

import java.lang.invoke.MethodHandles;
import java.util.TimerTask;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.SyncTaskExecutor;


public class BaconCacheExecutor extends TimerTask {

	protected SyncTaskExecutor syncTaskExecutor = new SyncTaskExecutor();
	private CompletionService executionService = new ExecutorCompletionService<>(syncTaskExecutor);

	Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	BaconCacheLoader baconCacheLoader;

	@Override
	public void run() {

		try {
			Future<String> result = executionService.submit(baconCacheLoader);

			String message = result.get();
			logger.info(message);
			try {
				executionService.take();

			} catch (InterruptedException e) {

				logger.error("BaconCacheLoader loading into cache {} was interrupted", baconCacheLoader.cacheName, e);
			}

		} catch (RejectedExecutionException e) {
			logger.error("BaconCacheLoader loading into cache {} was rejected by executor", baconCacheLoader.cacheName, e);

		} catch (NullPointerException npe) {
			logger.error("BaconCacheLoader loading into cache {} was null", baconCacheLoader.cacheName, npe);
		} catch (ExecutionException | InterruptedException ex) {
			logger.error("BaconCacheLoader loading into cache {} was not completed", baconCacheLoader.cacheName, ex.getCause());
		}

	}

	public void registerBaconCacheLoader(BaconCacheLoader baconCacheLoader) {
		this.baconCacheLoader = baconCacheLoader;
	}

}
