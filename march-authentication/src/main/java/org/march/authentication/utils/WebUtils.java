package org.march.authentication.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class WebUtils {
	
	public static String getValueFromCookie(HttpServletRequest req, String key){
		Cookie[] array = req.getCookies();
		if(array != null && array.length > 0){
			for(Cookie c : array){
				if(c.getName().equals(key)){
					return c.getValue();
				}
			}
		}
		return null;
	}
	
	public static Cookie getCookie(HttpServletRequest req, String key){
		Cookie[] array = req.getCookies();
		if(array != null && array.length > 0){
			for(Cookie c : array){
				if(c.getName().equals(key)){
					return c;
				}
			}
		}
		return null;
	}
	
	public static boolean isRemembered(HttpServletRequest req){
		if(WebUtils.getCookie(req, "rmbn") != null && WebUtils.getCookie(req, "rmbp") != null){
			return true;
		}
		return false;
	}
	
}
