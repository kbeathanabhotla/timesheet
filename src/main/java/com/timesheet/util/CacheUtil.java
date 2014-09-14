package com.timesheet.util;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

import com.timesheet.constants.ApplicationConstants;
import com.timesheet.domain.Person;

//@Component
public class CacheUtil {

	private static EhCacheCacheManager cacheMgr = null;
	
	@Autowired
	private EhCacheCacheManager ehCacheCacheManager;
	
	@PostConstruct
	public void init() {
		cacheMgr = ehCacheCacheManager;
	}
	
	public static Person getPerson(String authToken) {
		Cache cache = cacheMgr.getCache(ApplicationConstants.AUTH_TOKENS_CACHE_NAME);
		return cache.get(authToken, Person.class);
	}
	
	public static void savePersonToCache(String authToken, Person person) {
		Cache cache = cacheMgr.getCache(ApplicationConstants.AUTH_TOKENS_CACHE_NAME);
		cache.put(authToken, person);
	}
	
	public static void evictPersonFromCache(String authToken) {
		Cache cache = cacheMgr.getCache(ApplicationConstants.AUTH_TOKENS_CACHE_NAME);
		cache.evict(authToken);
	}
}
