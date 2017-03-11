package org.march.cache.core;

import org.march.cache.entity.CacheManagerFactory;
import org.march.cache.entity.DefaultCacheManagerFactory;
import org.march.cache.entity.MarchCache;
import org.march.cache.exception.MarchCacheException;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

public class MarchCacheUtils {
	
	private static final String DEFAULT_CACHE_NAME = Cache.DEFAULT_CACHE_NAME;
	
	/**
	 * 
	 * @return
	 * @throws IllegalStateException
	 * @throws ClassCastException
	 * @throws MarchCacheException
	 */
	public static MarchCache getDefaultCache() throws IllegalStateException, ClassCastException, MarchCacheException{
		DefaultCacheManagerFactory f = new DefaultCacheManagerFactory();
		MarchCache mc = new MarchCache();
		CacheManager mgr = f.get();
		return mc.setCache(mgr.getCache(DEFAULT_CACHE_NAME)).setManager(mgr);
	}
	
	/**
	 * 
	 * @param configFilePath
	 * @param cacheName
	 * @return
	 * @throws IllegalStateException
	 * @throws ClassCastException
	 * @throws MarchCacheException
	 */
	public static MarchCache getCacheWithParameters(String configFilePath, String cacheName) throws IllegalStateException, ClassCastException, MarchCacheException{
		CacheManagerFactory f = new CacheManagerFactory(configFilePath);
		MarchCache mc = new MarchCache();
		CacheManager mgr = f.get();
		return mc.setCache(mgr.getCache(cacheName)).setManager(mgr);
	}
	
	/**
	 * 
	 * @param cache
	 * @param configFilePath
	 * @throws MarchCacheException
	 */
	public static void addCustomizeCache(Cache cache, String configFilePath) throws MarchCacheException{
		CacheManagerFactory f = new CacheManagerFactory(configFilePath);
		CacheManager mgr = f.get();
		mgr.addCache(cache);
	}
	
	/**
	 * 
	 * @param cache
	 */
	public static void destoryCache(MarchCache cache){
		cache.getManager().removeCache(cache.getCache().getName());
		cache = null;
	}
}
