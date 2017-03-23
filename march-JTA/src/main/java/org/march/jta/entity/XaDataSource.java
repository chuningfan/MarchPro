package org.march.jta.entity;

import java.util.Properties;

public class XaDataSource extends com.atomikos.jdbc.AtomikosDataSourceBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8219991758601654850L;

	private String uniqueResourceName;
	
	private Properties xaProperties = getXaProperties();

	public String getUniqueResourceName() {
		return uniqueResourceName;
	}

	public void setUniqueResourceName(String uniqueResourceName) {
		this.uniqueResourceName = uniqueResourceName;
	}
	
	public void xaDataSourceConfig(XaDataSourceConfig config){
		xaProperties.setProperty("URL", config.getURL());
		xaProperties.setProperty("user", config.getUser());
		xaProperties.setProperty("password", config.getPassword());
		xaProperties.setProperty("pinGlobalTxToPhysicalConnection", config.isPinGlobalTxToPhysicalConnection().toString());
		
	}
	
}
