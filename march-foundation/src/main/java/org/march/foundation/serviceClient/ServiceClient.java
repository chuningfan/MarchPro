package org.march.foundation.serviceClient;

import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.march.foundation.entity.Demo;

public class ServiceClient {
	
	private static String URL = "http://localhost:8080/march-foundation/demoService/";
	
    public static void main(String[] args) {
//        Demo demo = new Demo("addr", "name", 12);
        //���б�����Ķ������ǽӿڣ� ������ʵ����
//        save(demo); //����ʵ����� ����
//        update(demo); //����ʵ�����update
//        findById("id"); //����Id��ѯ
//        delete("id"); //����Idɾ��
//        	findAll(); //����list
        
        	//�����Ǵ���list ������arrayList��������list����Ϊ���ǽӿڣ�
        	/*ArrayList<Demo> list = new ArrayList<Demo>();
        	list.add(demo);
        	batchSave(list);*/
    	
//    	daoFind("test");  //�����콻�� ��ԃ
    	
    	
    	ArrayList<Demo> list = new ArrayList<Demo>();
    	Demo demo1 = new Demo("addr", "name", 12);
    	Demo demo2 = new Demo("addr2", null, 14);
    	list.add(demo1);
    	list.add(demo2);
    	daoBatchSave(list);
    	
	}
    
    public static void save(Demo demo){
    	Client c = ClientBuilder.newClient();
        WebTarget t = c.target(URL+"save");
        Builder builder = t.request();
        Entity<Demo> entity= Entity.json(demo);
        Response resp = builder.buildPost(entity).invoke();
        resp.close();
    }
    
    public static void update(Demo demo){
    	Client c = ClientBuilder.newClient();
        WebTarget t = c.target(URL+"update");
        Builder builder = t.request();
        Entity<Demo> entity= Entity.json(demo);
        Response resp = builder.buildPut(entity).invoke();
        resp.close();
    }
    
    public static void findById(String id){
    	Client c = ClientBuilder.newClient();
        WebTarget t = c.target(URL + "find").path(id);
        Builder builder = t.request();
        Response resp = builder.get();
        Demo demo = resp.readEntity(Demo.class);
        System.out.println(demo.getAddr());
		System.out.println(demo.getName());
		System.out.println(demo.getAge());
        resp.close();
    }
    
    public static void delete(String id){
    	Client c = ClientBuilder.newClient();
        WebTarget t = c.target(URL + "delete").path("id");
        Builder builder = t.request();
        Response resp = builder.delete();
        resp.close();
    }
    
    public static void findAll(){
    	Client c = ClientBuilder.newClient();
        WebTarget t = c.target(URL + "findAll");
        Builder builder = t.request();
        Response resp = builder.get();
        ArrayList<Demo> list = resp.readEntity(new GenericType<ArrayList<Demo>>() {});
        for(Demo demo : list){
        	System.out.println(demo.getAddr());
    		System.out.println(demo.getName());
    		System.out.println(demo.getAge());
        }
        resp.close();
    }
    
    
    public static void batchSave(ArrayList<Demo> list){
    	Client c = ClientBuilder.newClient();
        WebTarget t = c.target(URL+"batchSave");
        Builder builder = t.request();
        Entity<ArrayList<Demo>> entity= Entity.json(list);
        Response resp = builder.buildPost(entity).invoke();
        resp.close();
    }
    
    public static void daoFind(String name){
    	Client c = ClientBuilder.newClient();
        WebTarget t = c.target(URL + "daoFind").path(name);
        Builder builder = t.request();
        Response resp = builder.get();
        Demo demo = resp.readEntity(Demo.class);
        if(demo != null){
	        System.out.println(demo.getAddr());
			System.out.println(demo.getName());
			System.out.println(demo.getAge());
        }else{
        	System.out.println("no data found!");
        }
        resp.close();
    }
    
    
    public static void daoBatchSave(ArrayList<Demo> list){
    	Client c = ClientBuilder.newClient();
        WebTarget t = c.target(URL+"daoBatchSave");
        Builder builder = t.request();
        Entity<ArrayList<Demo>> entity= Entity.json(list);
        Response resp = builder.buildPost(entity).invoke();
        resp.close();
    }
}

