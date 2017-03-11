package org.march.file.restriction;

import java.util.ArrayList;
import java.util.List;

import org.march.file.entity.MarchRestriction;

public class MarchRestrictionChecker {
	
	private static final List<MarchRestriction> INVALID_LIST = new ArrayList<MarchRestriction>();
	
	static{
		//TODO use utils jar to get restrictions from properties file
	}
	
	public static boolean check(String fileType, Long fileSize){
		if(INVALID_LIST.size() > 0){
			MarchRestriction target = null;
			for(MarchRestriction m : INVALID_LIST){
				if(m.getFileType().equalsIgnoreCase(fileType)){
					target = m;
					break;
				}
			}
			if(target == null){
				return true;
			}
			if(target.isInvalidFile()){
				return false;
			}else{
				if(fileSize.compareTo(fileSize) > 0){
					return false;
				}else{
					return true;
				}
			}
		}
		return true;
	}
	
}
