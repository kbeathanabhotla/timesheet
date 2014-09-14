package com.timesheet.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.timesheet.constants.ApplicationConstants;
import com.timesheet.domain.Person;

@Component
public class CacheUtil {

	@Autowired
	private CacheManager cacheManager;
	
	private static CacheManager ehCacheCacheManager;
	
	@PostConstruct
	public void init() {
		ehCacheCacheManager = cacheManager;
	}
	
	public static Person getPerson(String authToken) {
		return ehCacheCacheManager.getCache(ApplicationConstants.AUTH_TOKENS_CACHE_NAME).get(authToken, Person.class);
	}
	
	public static void savePersonToCache(String authToken, Person person) {
		ehCacheCacheManager.getCache(ApplicationConstants.AUTH_TOKENS_CACHE_NAME).put(authToken, person);
	}
	
	public static void evictPersonFromCache(String authToken) {
		ehCacheCacheManager.getCache(ApplicationConstants.AUTH_TOKENS_CACHE_NAME).evict(authToken);
	}
}
