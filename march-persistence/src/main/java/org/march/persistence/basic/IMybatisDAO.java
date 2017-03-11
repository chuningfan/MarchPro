package org.march.persistence.basic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

public interface IMybatisDAO<T> {
	 /** 
     * ���� 
     * @param statementName  
     * @param obj 
     * @throws DataAccessException 
     */  
    void addObject(String statementName, Object obj) throws DataAccessException;  
      
    /** 
     * ������IDɾ����¼ 
     * @param statementName 
     * @param objId 
     * @return 
     * @throws DataAccessException 
     */  
    int deleteObject(String statementName,Serializable objId) throws DataAccessException;  
      
    /** 
     * ɾ���������������м�¼ 
     * @param statementName 
     * @param map ����map 
     * @return 
     * @throws DataAccessException 
     */  
    int deleteForMap(String statementName,Map<String, Object> map) throws DataAccessException;  
      
    /** 
     * ������ID���в��� 
     * @param statementName 
     * @param objId ����ID 
     * @return 
     * @throws DataAccessException 
     */  
    Object findObject(String statementName,Serializable objId) throws DataAccessException;  
      
    /** 
     * ���¶��� 
     * @param statementName 
     * @param obj Ҫ���µĶ��� 
     * @return 
     * @throws DataAccessException 
     */  
    int updateObject(String statementName,T entity) throws DataAccessException;  
      
    /** 
     * ���Ķ�������/ͣ��״̬ 
     * @param statementName 
     * @param map 
     * @return 
     * @throws DataAccessException 
     */  
    int updateObjectState(String statementName,Map<String, Object> map) throws DataAccessException;  
      
    /** 
     * ͳ�Ʒ��������ļ�¼����Ŀ 
     * @param statementName 
     * @param filter sql������� 
     * @return 
     */  
    int getObjectCount(String statementName, String filter);  
      
    /** 
     * ͳ�Ʒ��������ļ�¼����Ŀ 
     * @param statementName 
     * @param map ��������map 
     * @return 
     */  
    int getObjectCount(String statementName, Map<String, Object> map);  
      
    /** 
     * ���ҷ��������Ķ��� 
     * @param statementName 
     * @param map ��������map 
     * @return 
     */  
    Object findObject(String statementName, Map<String, Object> map);  
      
    /** 
     *��ҳ��ѯ������������Ķ����б�  
     * @param statementName 
     * @param map ��������map 
     * @param skipResults �����ļ�¼ 
     * @param pageSize һҳ�ļ�¼���� 
     * @return 
     */  
    <R> List<R> listByPage(String statementName, Map<String, Object> map, int skipResults, int pageSize);  
      
    /** 
     * ��ҳ��ѯ������������Ķ����б�  
     * @param statementName 
     * @param filter sql������� 
     * @param skipResults �����ļ�¼ 
     * @param pageSize һҳ�ļ�¼���� 
     * @return 
     */  
    <R> List<R> listByPage(String statementName, String filter, int skipResults, int pageSize);  
      
    /** 
     * ��ѯ����������������Ķ����б�  
     * @param statementName 
     * @param filter sql������� 
     * @param skipResults �����ļ�¼ 
     * @param pageSize һҳ�ļ�¼���� 
     * @return 
     */  
    <R> List<R> list(String statementName, String filter);  
      
    /** 
     * ��ѯ����������������Ķ����б�  
     * @param statementName 
     * @param map ��������map 
     * @param skipResults �����ļ�¼ 
     * @param pageSize һҳ�ļ�¼���� 
     * @return 
     */  
    <R> List<R> list(String statementName, Map<String, Object> map);  
}
