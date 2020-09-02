package top.xuxing.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author xuxing
 * @date 2020/8/31
 */
public class MyJobOne implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("top.xuxing.job.MyJobOne 定时任务");
    }
}
