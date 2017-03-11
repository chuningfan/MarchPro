package org.march.schedule.core;

import org.march.schedule.entity.SysTask;
import org.march.schedule.exception.MarchScheduleException;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;

public class MarchSysScheduler {
	
	public static void addJobWithSys(SysTask task) {
		if(!task.isPrimary()){
			System.out.println("current server node is not primary, the schedule will be started on the primary server node!");
			return;
		}else{
			System.out.println("current server node is primary, task will be started!");
		}
        try {
        	String jobName = task.getJobName();
    		String jobGroupName = task.getJobGroupName();
    		String triggerGroupName = task.getTriggerGroupName();
    		String triggerName = task.getTriggerName();
    		Class<?> targetClass = task.getTargetClass();
    		String time = task.getExpression().toString();
    		Scheduler sched = task.getScheduler();
            JobDetail jobDetail = new JobDetail(jobName, jobGroupName, targetClass);// 任务名，任务组，任务执行类
            CronTrigger trigger = new CronTrigger(triggerName, triggerGroupName);// 触发器名,触发器组
            trigger.setCronExpression(time);
            sched.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } catch (MarchScheduleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
