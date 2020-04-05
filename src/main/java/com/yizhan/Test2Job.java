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
        //打印当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间:"+sdf.format(date));



    }
}
