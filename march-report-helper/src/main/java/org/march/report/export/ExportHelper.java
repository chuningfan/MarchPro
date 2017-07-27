package org.march.report.export;

import org.march.report.data.FileType;
import org.march.report.entity.ReturnData;

public abstract class ExportHelper<T> {

	protected abstract ReturnData<T> getDataToEx();
	
	protected void doExport(String fileName, String savePath, FileType fileType){
		
		switch(fileType){
			case HTML:	break;
			case XLS:	break;
			case CSV:	break;
		}
		
	} 
	
}
