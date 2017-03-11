package org.march.schedule.constract;

import org.quartz.SchedulerFactory;

public class ScheduleProperty {
	
	public static final SchedulerFactory SCHEDULE_FACTORY = new org.quartz.impl.StdSchedulerFactory();
	
	public static final String DEFAULT_JOB_GROUP_NAME = "default_job_group";
	
	public static final String DEFAULT_TRIGGER_GROUP_NAME = "default_trigger_group";
	
}
