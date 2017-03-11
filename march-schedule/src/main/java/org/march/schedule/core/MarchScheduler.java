package org.march.schedule.core;

import org.march.schedule.entity.MarchTask;
import org.march.schedule.exception.MarchScheduleException;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;

public class MarchScheduler {

	public static void addJobManually(MarchTask task) {
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

	public static void modifyJobTime(MarchTask task) {
		try {
			String triggerGroupName = task.getTriggerGroupName();
			String triggerName = task.getTriggerName();
			String time = task.getExpression().toString();
			Scheduler sched = task.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerName, triggerGroupName);
			if (trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				CronTrigger ct = (CronTrigger) trigger;
				ct.setCronExpression(time);
				sched.resumeTrigger(triggerName, triggerGroupName);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} catch (MarchScheduleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void removeJob(MarchTask task) {
		try {
			String jobName = task.getJobName();
			String jobGroupName = task.getJobGroupName();
			String triggerGroupName = task.getTriggerGroupName();
			Scheduler sched = task.getScheduler();
			sched.pauseTrigger(jobName, triggerGroupName);// 停止触发器
			sched.unscheduleJob(jobName, triggerGroupName);// 移除触发器
			sched.deleteJob(jobName, jobGroupName);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} catch (MarchScheduleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void startJobs(Scheduler sched) {
		try {
			sched.start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void shutdownJobs(Scheduler sched) {
		try {
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
