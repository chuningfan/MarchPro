package org.march.file.entity;

public class MarchRestriction {
	
	private String fileType;
	
	private Long fileSize;
	
	private boolean invalidFile = false;

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public boolean isInvalidFile() {
		return invalidFile;
	}

	public void setInvalidFile(boolean invalidFile) {
		this.invalidFile = invalidFile;
	}
	
}
