

import org.march.schedule.core.MarchScheduler;
import org.march.schedule.entity.MarchTask;

public class TestCase {
	
	@org.junit.Test
	public void test() {
        MarchTask task = new MarchTask();
        task.setExpression("0/1 * * * * ?");
        task.setJobGroupName("JobGroup");
        task.setJobName("Job");
        task.setTargetClass(Test.class);
        task.setTriggerGroupName("TriggerGroup");
        task.setTriggerName("Trigger");
        MarchScheduler.addJobManually(task);
	}
	
}
