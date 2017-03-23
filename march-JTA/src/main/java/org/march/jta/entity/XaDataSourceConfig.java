package org.march.jta.entity;

public class XaDataSourceConfig {
	
	private String URL;
	
	private String user;
	
	private String password;
	
	private Boolean pinGlobalTxToPhysicalConnection = true;

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean isPinGlobalTxToPhysicalConnection() {
		return pinGlobalTxToPhysicalConnection;
	}

	public void setPinGlobalTxToPhysicalConnection(Boolean pinGlobalTxToPhysicalConnection) {
		this.pinGlobalTxToPhysicalConnection = pinGlobalTxToPhysicalConnection;
	}
	
}
