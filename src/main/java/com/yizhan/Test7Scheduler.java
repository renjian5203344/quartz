package com.yizhan;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test7Scheduler {
    public static void main(String[] args) throws SchedulerException, InterruptedException {



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
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * *  ? *"))
                .build();


        //创建Scheduler实例
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();


//        scheduler.scheduleJob(jobDetail,trigger);
        System.out.println("scheduled time is:" + sdf.format(scheduler.scheduleJob(jobDetail,trigger)) );
/***示例代码一
 *  //scheduler执行2秒后自动挂起
 *  Thread.sleep(2000L);
 *  scheduler.standby();//scheduler暂时挂起
 *  //scheduler挂起3秒后，继续执行
 *  Thread.sleep(3000L);
 *  scheduler.start();//重新启动scheduler
 */


/****示例代码二
 *  //scheduler执行2秒后自动挂起
 *  Thread.sleep(2000L);
 *  scheduler.shutdown();//关闭scheduler,不可重启
 *  //shutdown(true)表示等待所有正在执行的job执行完毕之后，再关闭scheduler
 *  //shutdown(false)即shutdown()表示直接关闭scheduler
 *  //scheduler挂起3秒后，继续执行
 *  Thread.sleep(3000L);
 *  scheduler.start();//重新启动scheduler
 */


/****
 *   //scheduler执行2秒后自动挂起
 *   Thread.sleep(2000L);
 *   //shutdown(true)表示等待所有正在执行的job执行完毕之后，再关闭scheduler
 *   //shutdown(false)即shutdown()表示直接关闭scheduler
 *   scheduler.shutdown(true);
 *   System.out.println("scheduler is shut down?" + scheduler.isShutdown());//打印是否停止
 */


    //scheduler执行2秒后自动挂起
    Thread.sleep(2000L);
    //shutdown(true)表示等待所有正在执行的job执行完毕之后，再关闭scheduler
    //shutdown(false)即shutdown()表示直接关闭scheduler
    scheduler.shutdown(true);
    System.out.println("scheduler is shut down?" + scheduler.isShutdown());//打印是否停止






    }
}

