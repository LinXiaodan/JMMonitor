package com.lxd.monitor.Job;

import org.quartz.*;

// 在Job被执行结束后，将会更新JobDataMap，这样下次Job执行后就会使用新的值而不是初始值
@PersistJobDataAfterExecution
// 同一时间将只有一个Job实例被执行, 为了避免并发问题导致数据紊乱,建议这两个注解一起使用
@DisallowConcurrentExecution
public class SpiderJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException{
        System.out.println("set spider");
    }
}
