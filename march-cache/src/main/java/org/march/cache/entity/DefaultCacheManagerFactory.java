package org.march.cache.entity;

import org.march.cache.exception.MarchCacheException;

import net.sf.ehcache.CacheManager;

/**
 * 
 * @author Vic.Chu
 *
 *	this class is for cache manager with default settings 
 */
public class DefaultCacheManagerFactory implements ICacheManagerFactory {

	/**
	 * @return a cache manager configured by configuration file in the path
	 */
	public CacheManager get() throws MarchCacheException {
		CacheManager mgr = CacheManager.create(ICacheManagerFactory.DEFAULT_CONFIG_PATH);
		if(mgr == null){
			throw new MarchCacheException("No cache manager was found with the config file path!");
		}
		return mgr;
	}

}
