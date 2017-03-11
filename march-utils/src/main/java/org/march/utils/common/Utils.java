package org.march.utils.common;

public class Utils {
	
	public static boolean isEmpty(String string){
		boolean flag = false;
		if(string == null || "".equals(string) || "".equals(string.trim()) || "".equals(string.replaceAll(" +", ""))){
			return true;
		}
		return flag;
	}
	
	
	
}
