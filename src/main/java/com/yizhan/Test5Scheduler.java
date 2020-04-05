package com.yizhan;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test5Scheduler {
    public static void main(String[] args) throws SchedulerException {



        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current time Is::"+sdf.format(date));

        //创建JobDetail实例，将该实例与TestJob绑定
        JobDetail jobDetail = JobBuilder.newJob( Test5Job.class)
                .withIdentity("myjob","group1")
                .build();


        //获取距离当前时间4秒钟之后的具体时间
        date.setTime(date.getTime() + 4000L);
        //获取距离当前时间6秒钟之后的具体时间
        Date endDate = new Date();
        endDate.setTime(endDate.getTime() + 6000L);

        /**
         * 距离当前时间4秒钟后首次执行任务，之后每隔两秒中重复执行一次任务
         * 直到当前6秒中之后为止-startAt和endAt优先于withRepeatCount
         */
        //创建SimpleTrigger实例,触发job执行
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger","group1")
                .startAt(date)
                .endAt(endDate)
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(2)//设置频率
                        .withRepeatCount(3))//设置首次触发之后接着触发三次。
                .build();

        //创建Scheduler实例（Factory）
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();


        scheduler.scheduleJob(jobDetail,trigger);



}
}
