package com.yizhan;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test7Job implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {


       try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /***示例三代码
         * 模拟job正在执行，scheduler运行2S后关闭。模拟执行时间超出2S(当scheduler关闭时，还有job正在执行)
         */
        //打印当前执行时间，格式为:2020-0101 00:00:00
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Exec Time Is:" + sdf.format(date));

        System.out.println("Hello Word");



    }
}