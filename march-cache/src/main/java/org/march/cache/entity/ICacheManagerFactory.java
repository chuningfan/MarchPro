package org.march.cache.entity;

import org.march.cache.exception.MarchCacheException;

import net.sf.ehcache.CacheManager;

/**
 * 
 * @author Vic.Chu
 *
 *	this interface is used for creating different cache managers
 */
public interface ICacheManagerFactory {
	
	static final String DEFAULT_CONFIG_PATH = "classpath:default-ehcache.xml";
	
	CacheManager get() throws MarchCacheException;
	
}
