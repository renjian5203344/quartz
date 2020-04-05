package com.yizhan;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test3Scheduler {
    public static void main(String[] args) throws SchedulerException {



        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current time Is::"+sdf.format(date));

        //创建一个JobDetail实例，将该实例与 TestJob绑定
        JobDetail jobDetail = JobBuilder.newJob( Test3Job.class)
                .withIdentity("myjob","group1")
                .build();

        //创建SimpleTrigger实例，触发job执行
        //距离当前时间4S后执行，且执行一次任务

        //获取距离当前时间4S过后的时间
        date.setTime(date.getTime()+4000L);

        SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger","group1")
                .startAt(date)
                .build();

        //创建Scheduler实例（Factory）
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();



        scheduler.scheduleJob(jobDetail,trigger);


    }
}
