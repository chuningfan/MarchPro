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
        //所有被传入的对象不能是接口， 必须是实现类
//        save(demo); //传入实体对象 保存
//        update(demo); //传入实体对象update
//        findById("id"); //传入Id查询
//        delete("id"); //传入Id删除
//        	findAll(); //返回list
        
        	//以下是传入list 必须是arrayList（不能是list，因为它是接口）
        	/*ArrayList<Demo> list = new ArrayList<Demo>();
        	list.add(demo);
        	batchSave(list);*/
    	
//    	daoFind("test");  //數據庫交互 查詢
    	
    	
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

