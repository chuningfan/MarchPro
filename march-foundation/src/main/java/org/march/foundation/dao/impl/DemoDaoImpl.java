package org.march.foundation.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.march.foundation.dao.DemoDao;
import org.march.persistence.dao.MybatisBaseDAO;
import org.springframework.stereotype.Repository;

@Repository("demoDao")
public class DemoDaoImpl<Demo> extends MybatisBaseDAO<org.march.foundation.entity.Demo> implements DemoDao {
	
	@Resource
	private SqlSessionFactory sqlSessionFactory;

	public org.march.foundation.entity.Demo find(String name) {
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("name", name);
		org.march.foundation.entity.Demo d =  (org.march.foundation.entity.Demo) super.findObject("find", condition);
		return d;
	}

	public void save(org.march.foundation.entity.Demo demo) {
		super.addObject("save", demo);
	}
	
	

}
