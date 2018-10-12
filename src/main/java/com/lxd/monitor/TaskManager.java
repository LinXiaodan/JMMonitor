package com.lxd.monitor;

import com.lxd.monitor.Job.*;
import com.lxd.monitor.Util.TimeUtil;
import com.lxd.monitor.constant.JobType;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.logging.Logger;

public class TaskManager {
    private static SchedulerFactory ssf = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "TASK_JOBGROUP_NAME";
    private static String TRIGGER_GROUP_NAME = "TASK_TRIGGERGROUP_NAME";
    private static Logger managerLogger = Logger.getLogger("manager_logger");
    private static String NAME_PREFIX = "task_";

    public static String addJob(int job_id, String cron, int job_type){
        try{
            Scheduler scheduler = ssf.getScheduler();

            JobDetail job;
            if(job_type == JobType.SPIDER_JOB){
                job = JobBuilder.newJob(SpiderJob.class)
                        .withIdentity(NAME_PREFIX + job_id, JOB_GROUP_NAME)
                        .build();
            }else if(job_type == JobType.HANDLE_JOB){
                job = JobBuilder.newJob(HandleJob.class)
                        .withIdentity(NAME_PREFIX + job_id, JOB_GROUP_NAME)
                        .build();
            }else{
                return "启动失败， id: " + job_id;
            }

            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(NAME_PREFIX + job_id, TRIGGER_GROUP_NAME)
                    .withSchedule(scheduleBuilder)
                    .build();
            scheduler.scheduleJob(job, trigger);

            if(!scheduler.isShutdown()){
                scheduler.start();
                managerLogger.info("添加并启动任务，id：" + job_id);
            }
            return "启动成功， id: " + job_id;
        }catch (Exception e){
            e.printStackTrace();
            return "启动失败， id: " + job_id;
        }
    }

//    public static String[] addJob(int job_id, String cron, String uuid, String startProcessAddress, String startProcessPath, String addExecPath, String server_port){
//        try{
//            Scheduler scheduler = ssf.getScheduler();
//            JobDetail job = JobBuilder.newJob(TaskJob.class)
//                    .withIdentity(NAME_PREFIX + job_id, JOB_GROUP_NAME)
//                    .build();
//
//            job.getJobDataMap().put("job_id", job_id);
//            job.getJobDataMap().put("uuid", uuid);
//            job.getJobDataMap().put("startProcessAddress", startProcessAddress);
//            job.getJobDataMap().put("startProcessPath", startProcessPath);
//            job.getJobDataMap().put("addExecPath", addExecPath);
//            job.getJobDataMap().put("serverPort", server_port);
//
//            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron).withMisfireHandlingInstructionDoNothing();
//            Trigger trigger = TriggerBuilder.newTrigger()
//                    .withIdentity(NAME_PREFIX + job_id, TRIGGER_GROUP_NAME)
//                    .withSchedule(scheduleBuilder)
//                    .build();
//            scheduler.scheduleJob(job, trigger);
//
//            if(!scheduler.isShutdown()){
//                scheduler.start();
//                managerLogger.info("添加并启动任务，id：" + job_id + "，uuid：" + uuid);
//            }
//            String now_time = TimeUtil.getFormatDate(System.currentTimeMillis());
//            String[] result = {now_time, "启动任务成功"};
//            return result;
//        }catch (Exception e){
//            managerLogger.severe(e.toString());
//            String now_time = TimeUtil.getFormatDate(System.currentTimeMillis());
//            String[] result = {now_time, "启动任务失败"};
//            return result;
//        }
//    }

    public static String[] removeJob(int job_id){
        TriggerKey triggerKey = TriggerKey.triggerKey(NAME_PREFIX + job_id, TRIGGER_GROUP_NAME);
        JobKey jobKey = JobKey.jobKey(NAME_PREFIX + job_id, JOB_GROUP_NAME);
        try{
            Scheduler scheduler = ssf.getScheduler();
            Trigger trigger = scheduler.getTrigger(triggerKey);
            if(trigger == null){
                String now_time = TimeUtil.getFormatDate(System.currentTimeMillis());
                String[] result = {now_time, "停止任务失败：该任务未启动"};
                return result;
            }

            scheduler.pauseTrigger(triggerKey);//停止触发器
            scheduler.unscheduleJob(triggerKey);//移除触发器
            scheduler.deleteJob(jobKey); //删除任务

            managerLogger.info("移除任务，编号：" + job_id);
            String now_time = TimeUtil.getFormatDate(System.currentTimeMillis());
            String[] result = {now_time, "停止任务成功"};
            return result;
        }catch (Exception e){
            managerLogger.severe(e.toString());
            String now_time = TimeUtil.getFormatDate(System.currentTimeMillis());
            String[] result = {now_time, "停止任务失败"};
            return result;
        }
    }

    public static boolean pauseAll(){
        try{
            Scheduler scheduler = ssf.getScheduler();
            scheduler.shutdown();
            managerLogger.info("暂停所有任务");
            return true;
        }catch (Exception e){
            managerLogger.severe(e.toString());
            return false;
        }

    }
}
