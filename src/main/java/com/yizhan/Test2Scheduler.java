package com.yizhan;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test2Scheduler {

    public static void main(String[] args) throws SchedulerException {


        //打印当前时间
        Date date = new Date();
        Date endDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current time Is::"+sdf.format(date));

        //创建一个JobDetail实例，将该实例与 TestJob绑定
        JobDetail jobDetail = JobBuilder.newJob( Test2Job.class)
                .withIdentity("myjob","group1")
                .build();

        //获取当前时间3秒后的时间
        date.setTime(date.getTime()+3000);
        //获取当前时间6秒后的时间
        endDate.setTime(endDate.getTime()+6000);



        //创建Trigger实例(触发job执行) 定义执行时间、会不会重复执行、或执行几次终止
        //定义该job开始时间为当前时间3秒以后，结束时间为当前时间6S以后
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger","group1")
                .startAt(date)
                .endAt(endDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2).repeatForever()).build();

        //创建Scheduler实例（Factory）
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();



        scheduler.scheduleJob(jobDetail,trigger);


    }
}
