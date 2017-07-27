package org.march.report.test.format;

import org.march.report.service.BaseFormat;

public class TestFormat extends BaseFormat {

	@Override
	public Object val(Object originalValue) {
		if(originalValue != null){
			originalValue = originalValue.toString() + "******";
		}
		return originalValue;
	}

}
