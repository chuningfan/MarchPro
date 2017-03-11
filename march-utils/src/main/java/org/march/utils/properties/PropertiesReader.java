package org.march.utils.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {
	
	private static final Map<String, Properties> proMap = new HashMap<String, Properties>();
	
	private static final String CLASS_PATH = "classpath:";
	
	private String fileName;
	
	public PropertiesReader(String fileName){
		this.fileName = CLASS_PATH + fileName;
	}
	
	public Properties getOrLoad() throws Exception{
		if(proMap.containsKey(fileName)){
			return proMap.get(fileName);
		}
		InputStream in = PropertiesReader.class.getClassLoader().getResourceAsStream(fileName);
		try {
			Properties pro = new Properties();
			pro.load(in);
			proMap.put(fileName, pro);
			return pro;
		} catch (IOException e) {
			throw new Exception("Loading properties file error!");
		}
	}
	
}
