package org.march.schedule.entity;

/**
 * 
 * @author Vic.Chu
 *
 */
public class SysTask extends MarchTask {
	
	private boolean isPrimary;
	
	public SysTask(){
		boolean isPrimary = false;//TODO get value from config file in classpath
		this.isPrimary = isPrimary;
	}
	
	public boolean isPrimary() {
		return isPrimary;
	}
	
}
