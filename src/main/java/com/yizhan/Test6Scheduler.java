package com.yizhan;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test6Scheduler {
    public static void main(String[] args) throws SchedulerException {



        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current time Is::"+sdf.format(date));

        //创建JobDetail实例，将该实例与TestJob绑定
        JobDetail jobDetail = JobBuilder.newJob( Test5Job.class)
                .withIdentity("myjob","group1")
                .build();


        //每秒钟触发一个任务  * * * * *  ? *
        CronTrigger trigger = (CronTrigger) TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger","group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 10 10 ? * * 2020"))
                .build();


        /**
         * cron表达式
         * 用户配置CronTrigger实例
         * 是由7哥子表达式组成的额字符串，表示了时间表的详细信息
         * 格式：[秒] [分] [小时] [日] [月] [周] [年]
         * 都允许的特殊字符
         * ,表示或
         * -表示至,between
         * *表示每X
         * /表示每,比如每天的几分钟执行、几小时执行
         *
         *
         * quartz三大核心
         * 调度器：Scheduler定期执行job
         * 任务： job（需要实现的业务逻辑）
         * 触发器：告诉Scheduler什么时候执行任务
         * Scheduler维护了jobDetails和triggers的注册表，一旦在Scheduler注册过了，当定时任务触发时间一到，调度程序就会执行预先定义好的job
         */
        //1. 2017年内每天10点15分触发一次
        //0 15 10 ? * * 2017
        //2. 每天的14点整至14点59分55秒，以及18点整至18点59分55秒，每5秒钟触发一次
        //0/5 * 14,18 * * ?
        //3.每周一至周五10点15分触发一次
        //4.每月最后一天的10点15分触发一次
        //5.每月第三个周五的10点15分触发一次


        //创建Scheduler实例（Factory）
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();


        scheduler.scheduleJob(jobDetail,trigger);



    }
}
