package org.march.authentication.token;

import java.io.IOException;

import org.march.authentication.constract.ValidationConstract;

public class TokenHelper {
	
	
	public static boolean check(String loginName, String token){
		String str = null;
		try {
			str = MD5Utils.desDecrypt(token, null);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(str.split(ValidationConstract.TOKEN_SUFFIX)[0].equals(loginName)
//				&&
//				(current.compareTo(Long.valueOf(str.split(ValidationConstract.TOKEN_SUFFIX)[1])))<0
				){
			return true;
		}
		return false;
	}
	
	public static String getLoginName(String token){
		try {
			String str = MD5Utils.desDecrypt(token, null);
			return str.split(ValidationConstract.TOKEN_SUFFIX)[0];
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Long getLoginTime(String token){
		try {
			String str = MD5Utils.desDecrypt(token, null);
			return Long.valueOf(str.split(ValidationConstract.TOKEN_SUFFIX)[1]);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String createToken(String loginName, Long loginTime){
		String readyToConvert = loginName + ValidationConstract.TOKEN_SUFFIX + loginTime;
		String token = null;
		try {
			token = MD5Utils.desEncrypt(readyToConvert, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	
}
