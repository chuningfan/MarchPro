package org.march.foundation.service;

import java.util.List;

import org.march.foundation.entity.Demo;

/**
 * 
 * @author Vic.Chu
 *	RESTFUL ���Ľӿ� ����ɾ�Ĳ��ж��ص�����ʽ��
 *	�� post
 *	ɾ delete
 *	�� put
 *	�� get
 *
 *	��Ϊ���������Ƿ������Բ���Ҫcontroller�㣬ֻ��service�����ӡ�
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
