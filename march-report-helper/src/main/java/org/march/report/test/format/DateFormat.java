package org.march.report.test.format;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.march.report.service.BaseFormat;

public class DateFormat extends BaseFormat {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
	
	@Override
	public Object val(Object originalValue) {
		
		Date date = (Date) originalValue;
		
		return sdf.format(date);
	}
	
}
