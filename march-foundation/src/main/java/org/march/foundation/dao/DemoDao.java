package org.march.foundation.dao;

import org.march.foundation.entity.Demo;

public interface DemoDao {

	void save(Demo demo);
	
	org.march.foundation.entity.Demo find(String name);
	
}
