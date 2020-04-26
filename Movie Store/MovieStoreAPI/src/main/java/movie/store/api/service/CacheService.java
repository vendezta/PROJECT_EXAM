package movie.store.api.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

	private static final Logger logger = LoggerFactory.getLogger(CacheService.class);

	@Autowired
    CacheManager cacheManager;

    public void evictAllCaches() {
        cacheManager.getCacheNames().stream()
          .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }


    @Scheduled(cron="0 0 0 * * ?")
    public void evictAllcachesAtIntervals() {
    	logger.debug("Clear all caches by CacheService on "+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").format(new Date()));
        evictAllCaches();
    }
    
}
