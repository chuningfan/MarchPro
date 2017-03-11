package org.march.file.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.march.file.constract.Global;
import org.march.file.entity.MarchFileDTO;
import org.march.file.exception.MarchFileException;

public class MarchFileManager {
	
	public static MarchFileDTO upload(File file) throws IOException, MarchFileException{
		MarchFileDTO dto = new MarchFileDTO();
		if(file.isFile()){
			String name = file.getName().substring(0,file.getName().lastIndexOf("."));
			String fileType = file.getName().substring(file.getName().lastIndexOf(".")+1);
			Serializable id = UUID.randomUUID();
			Long uploadTime = System.currentTimeMillis();
			String storePath = "C:/Users/chun08"; //通过utils 读取配置文件
			Long fileSize = file.length();
			String storeName = id + Global.SUFIX + fileType;
			File directory = new File(storePath);
			if(!directory.exists()){
				directory.mkdirs();
			}
			File fullDirectory = new File(storePath + Global.PATH + storeName);
			if(!fullDirectory.exists()){
				fullDirectory.createNewFile();
			}
			FileUtils.copyFile(file, fullDirectory);
			dto.setFileSize(fileSize);
			dto.setFileType(fileType);
			dto.setId(id);
			dto.setName(name);
			dto.setUploadTime(uploadTime);
			dto.setFileSize(fileSize);
			dto.setStoreName(storeName);
			dto.setStorePath(storePath);
			dto.setFullpath(storePath + Global.PATH + storeName);
		}else{
			throw new MarchFileException("The input parameter must be a file!");
		}	
		return dto;
	}
	
	public static void removeFile(String fullPath) throws IOException{
		File file = new File(fullPath);
		if(file.exists()){
			file.delete();
		}
	}
	
	public static String readFileAsString(File file, String encoding) throws IOException{
		String returnRes = null;
		if(file.exists()){
			returnRes = FileUtils.readFileToString(file, encoding);
		}
		return returnRes;
	}
	
	public static InputStream readFileAsInputStream(File file) throws IOException{
		InputStream in = FileUtils.openInputStream(file);
		return in;
	}
	
	public static byte[] readFileAsByteArray(File file) throws IOException{
		return FileUtils.readFileToByteArray(file);
	}
	
	public static String readStoredFileAsString(String fullPath, String encoding) throws IOException{
		File file = new File(fullPath);
		return readFileAsString(file, encoding);
	}
	
	public static InputStream readStoredFileAsStream(String fullPath) throws IOException{
		File file = new File(fullPath);
		return readFileAsInputStream(file);
	}
	
	public static byte[] readStoredFileAsByteArray(String fullPath) throws IOException{
		File file = new File(fullPath);
		return readFileAsByteArray(file);
	}
	
}
