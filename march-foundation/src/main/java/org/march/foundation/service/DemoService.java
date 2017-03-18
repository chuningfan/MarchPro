package org.march.foundation.service;

import java.util.List;

import org.march.foundation.entity.Demo;

/**
 * 
 * @author Vic.Chu
 *	RESTFUL 风格的接口 对增删改查有独特的请求方式：
 *	增 post
 *	删 delete
 *	改 put
 *	查 get
 *
 *	因为我们做的是服务，所以不需要controller层，只到service层足矣。
 */
public interface DemoService {
	
	void save(Demo demo);
	
	void delete(String id);
	
	void update(Demo demo);
	
	Demo find(String id);
	
	List<Demo> findAll();
	
	Demo daoFind(String name);
	
	void daoBatchSave(List<Demo> list);
}
