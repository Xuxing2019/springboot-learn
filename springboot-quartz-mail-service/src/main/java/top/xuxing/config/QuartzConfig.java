package top.xuxing.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.xuxing.job.MyJob;

/**
 * @author xuxing
 * @date 2020/8/31
 */
@Configuration
public class QuartzConfig {
    /**
     * 创建Job
     * @return
     */
    @Bean
    public JobDetail jobDetail(){
        // .storeDurably() 可持久化
       return JobBuilder.newJob(MyJob.class).storeDurably().build();
    }
    @Bean
    public Trigger trigger(){
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 1-53 * * * ? ");
        return TriggerBuilder.newTrigger().startNow().forJob(jobDetail()).withSchedule(cronScheduleBuilder).build();
    }
}
