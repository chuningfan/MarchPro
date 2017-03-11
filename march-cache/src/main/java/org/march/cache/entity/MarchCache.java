package org.march.cache.entity;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 
 * @author Vic.Chu
 *
 *	get this object through MarchCacheUtils and the methods below are all allowed to be used
 */
public class MarchCache {
	
	private Cache cache;

	private CacheManager manager;
	
	public Cache getCache() {
		return cache;
	}

	public MarchCache setCache(Cache cache) {
		this.cache = cache;
		return this;
	}
	
	public CacheManager getManager() {
		return manager;
	}

	public MarchCache setManager(CacheManager manager) {
		this.manager = manager;
		return this;
	}

	public Element getElement(Object key){
		return cache.get(key);
	}
	
	public MarchCache putElemet(Element element){
		cache.put(element);
		return this;
	}
	
	public MarchCache removeElement(Object key){
		cache.remove(key);
		return this;
	}
	
	public MarchCache removeAll(){
		cache.removeAll();
		return this;
	}
	
	public MarchCache removeByKeys(Object...keys){
		if(keys != null){
			for(Object key : keys){
				removeElement(key);
			}
		}
		return this;
	}
	
	public MarchCache putElements(Element...elements){
		if(elements != null){
			for(Element element : elements){
				putElemet(element);
			}
		}
		return this;
	}
	
	public boolean keyExist(Object key){
		return cache.isKeyInCache(key);
	}
	
	
	public MarchCache putElement(Object key, Object value){
		Element ele = new Element(key, value);
		this.putElemet(ele);
		return this;
	}
	
	
	public MarchCache remove(Element element){
		cache.removeElement(element);
		return this;
	}
	
	public MarchCache removeElements(Element...elements){
		if(elements != null){
			for(Element element : elements){
				this.remove(element);
			}
		}
		return this;
	}
	
	public Object getElementValue(Object key){
		return cache.get(key).getObjectValue();
	}
	
	public MarchCache putElement(Object key, Object value, int liveSeconds){
		Element ele = new Element(key, value);
		ele.setEternal(false);
		ele.setTimeToLive(liveSeconds);
		ele.setTimeToIdle(1);
		this.putElemet(ele);
		return this;
	}
	
}
