package com.yizhan;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test2Scheduler {

    public static void main(String[] args) throws SchedulerException {

        //创建一个JobDetail实例，将该实例与 TestJob绑定
        JobDetail jobDetail = JobBuilder.newJob(TestJob.class)
                .withIdentity("myjob","group1")
                .build();
/*****
 *      System.out.println(" 【jobDetail】name:" + jobDetail.getKey().getName());
 *      System.out.println(" 【jobDetail】group:" + jobDetail.getKey().getGroup());
 *      System.out.println(" 【jobDetail】group:" + jobDetail.getJobClass().getName());
 *
 */

        //创建Trigger实例(触发job执行) 定义执行时间、会不会重复执行、或执行几次终止
        //定义该job立即执行、 并且每隔2S执行一次。
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger","group1")
                .startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2).repeatForever()).build();

        //创建Scheduler实例（Factory）
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();

        //打印当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间:"+sdf.format(date));

        scheduler.scheduleJob(jobDetail,trigger);


    }
}
