package org.march.report.service.impl;

import org.march.report.service.BaseFormat;

/**
 * 
 * @author Vic Chu
 *
 */
public class DefaultFormatter extends BaseFormat {

	@Override
	public Object val(Object originalValue) {
		return originalValue;
	}

}
