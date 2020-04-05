package com.yizhan;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test5Job implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //打印当前执行时间，格式为2020-0101 00：00：00
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Exec Time Is:" + sdf.format(date));

        System.out.println("Hello Word");



    }
}