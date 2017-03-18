package org.march.foundation.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.march.foundation.dao.DemoDao;
import org.march.foundation.entity.Demo;
import org.march.foundation.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("demoService")
@Path(value="/demoService")
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	private DemoDao demoDao;
	
	@POST  
    @Path("/save")
 	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void save(Demo demo) {
		System.out.println(demo.getAddr());
		System.out.println(demo.getName());
		System.out.println(demo.getAge());
	}
	@DELETE  
    @Path("/delete/{id}")
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void delete(@PathParam(value = "id") String id) {
		System.out.println("delete: parameter is " + id);
	}
	
	
	@PUT  
    @Path("/update")
 	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void update(Demo demo) {
		demo.setAge(demo.getAge()+1);
		System.out.println(demo.getAddr());
		System.out.println(demo.getName());
		System.out.println(demo.getAge());
	}
	
	@GET  
    @Path("/find/{id}")  
	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Demo find(@PathParam(value = "id") String id) {
		System.out.println("find: parameter is " + id);
		Demo demo = new Demo();
		demo.setAddr("addr");
		demo.setAge(12);
		demo.setName("name");
		return demo;
	}
	
	
	@GET  
    @Path("/findAll")  
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Demo> findAll() {
		Demo d1 = new Demo("ad1","nd1",1);
		Demo d2 = new Demo("ad2","nd2",2);
		Demo d3 = new Demo("ad3","nd3",3);
		Demo d4 = new Demo("ad4","nd4",4);
		List<Demo> list = new ArrayList<Demo>();
		list.add(d1);
		list.add(d2);
		list.add(d3);
		list.add(d4);
		return list;
	}
	
	@POST  
    @Path("/batchSave")
 	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void batchSave(ArrayList<Demo> list) {
		System.out.println(list.size());
	}
	
	@GET  
    @Path("/daoFind/{name}")  
	@Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Demo daoFind(@PathParam(value = "name") String name) {
		Demo d = demoDao.find(name);
		return d;
	}
	
	@Transactional
	@POST  
    @Path("/daoBatchSave")
 	@Consumes( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void daoBatchSave(List<Demo> list) {
		for(int i = 0; i < list.size(); i ++){
			Demo d = list.get(i);
			demoDao.save(d);
			//œyÔ‡ÊÂ„Õ¿ØÖÆ
//			if(i == 1){
//				Integer.parseInt("x");
//			}
		}
	}
	
}
