package com.lxd.monitor.Job;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

// 在Job被执行结束后，将会更新JobDataMap，这样下次Job执行后就会使用新的值而不是初始值
@PersistJobDataAfterExecution
// 同一时间将只有一个Job实例被执行, 为了避免并发问题导致数据紊乱,建议这两个注解一起使用
@DisallowConcurrentExecution
public class TaskJob implements Job {
    private Logger logger = Logger.getLogger("job_logger");

    public void execute(JobExecutionContext context) throws JobExecutionException{
        logger.info("--------start----------");
        String jobName = context.getJobDetail().getKey().getName();
        String jobGroup = context.getJobDetail().getKey().getGroup();
        String triggerName = context.getTrigger().getKey().getName();
        String triggerGroup = context.getTrigger().getKey().getGroup();
        JobDataMap map = context.getJobDetail().getJobDataMap();
        int job_id = map.getInt("job_id");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        logger.info("job_id: " + job_id + ", uuid: " + map.getString("uuid") + " 正在执行...");
        logger.info("触发器key:" + triggerGroup + ".." + triggerName + "正在执行...");
        logger.info("任务key: " + jobGroup + ".." + jobName + "正在执行， 执行时间：" + simpleDateFormat.format(Calendar.getInstance().getTime()));

    }
}
