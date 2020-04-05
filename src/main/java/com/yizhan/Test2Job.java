package com.yizhan;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test2Job implements Job {
    private String  message;
    private Float FloatJobValue;
    private Double DoubleTriggerValue;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Float getFloatJobValue() {
        return FloatJobValue;
    }

    public void setFloatJobValue(Float floatJobValue) {
        FloatJobValue = floatJobValue;
    }

    public Double getDoubleTriggerValue() {
        return DoubleTriggerValue;
    }

    public void setDoubleTriggerValue(Double doubleTriggerValue) {
        DoubleTriggerValue = doubleTriggerValue;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //打印当前执行时间，格式为2020-0101 00：00：00
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Exec Time Is:"+sdf.format(date));

        Trigger currentTrigger = context.getTrigger();
        System.out.println("Start Time is: " + sdf.format(currentTrigger.getStartTime()));
        System.out.println("End Time is: " + sdf.format(currentTrigger.getEndTime()));
        JobKey jobKey = currentTrigger.getJobKey();
        System.out.println("JobKey info===" + " jobName:" + jobKey.getName()
                +" ,jobGroup: "+ jobKey.getGroup());






    }
}
