package org.march.schedule.entity;

import org.march.schedule.constract.ScheduleProperty;
import org.march.schedule.exception.MarchScheduleException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;

/**
 * 
 * @author Vic.Chu
 *
 *	This class is only for starting a task manually
 */
public class MarchTask {
	
	private String jobGroupName;
	
	private String triggerGroupName;
	
	private Class<?> targetClass;
	
	private Scheduler scheduler;
	
	private String jobName;
	
	private String expression;
	
	private String triggerName;

	public String getJobGroupName() {
		if(jobGroupName == null){
			return ScheduleProperty.DEFAULT_JOB_GROUP_NAME;
		}
		return jobGroupName;
	}

	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	public String getTriggerGroupName() {
		if(triggerGroupName == null){
			return ScheduleProperty.DEFAULT_TRIGGER_GROUP_NAME;
		}
		return triggerGroupName;
	}

	public void setTriggerGroupName(String triggerGroupName) {
		this.triggerGroupName = triggerGroupName;
	}

	public Class<?> getTargetClass() throws MarchScheduleException {
		if(targetClass == null){
			throw new MarchScheduleException("Schedule: target class is not allowed to be null");
		}
		return targetClass;
	}

	public void setTargetClass(Class<?> targetClass) {
		this.targetClass = targetClass;
	}

	public Scheduler getScheduler() throws SchedulerException {
		if(scheduler == null){
			this.scheduler = ScheduleProperty.SCHEDULE_FACTORY.getScheduler();
		}
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public String getJobName() throws MarchScheduleException {
		if(jobName == null){
			throw new MarchScheduleException("Schedule: job name is not allowed to be null");
		}
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getTriggerName() throws MarchScheduleException {
		if(triggerName == null){
			throw new MarchScheduleException("Schedule: trigger name is not allowed to be null");
		}
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public String getExpression() throws MarchScheduleException {
		if(expression == null){
			throw new MarchScheduleException("Schedule: expression is not allowed to be null");
		}
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
}
