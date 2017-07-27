package org.march.report.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.march.report.service.impl.DefaultFormatter;

@Target({ java.lang.annotation.ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ReportDataMarker {

	public String displayName() default "";
	
	public Class<?> format() default DefaultFormatter.class;
	
	public int queryIndex();
	
}
