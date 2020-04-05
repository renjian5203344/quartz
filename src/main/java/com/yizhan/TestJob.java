package com.yizhan;
import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //打印当前时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间:"+sdf.format(date));

        /**
         *  具体业务逻辑
         *  System.out.println("Test yizhan!");
         */
        JobKey jobKey =  jobExecutionContext.getJobDetail().getKey();
        System.out.println("My Job name and group are: " + "[" + jobKey.getName() + " ," + jobKey.getGroup() + "]");

        TriggerKey triKey = jobExecutionContext.getTrigger().getKey();

        System.out.println("My Trigger name and  group are: " + "[" + triKey.getName() + " ," + triKey.getGroup() + "]");
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        JobDataMap tdataMap = jobExecutionContext.getTrigger().getJobDataMap();


        String jobMsg = dataMap.getString("message");
        float jobFloatValue = dataMap.getFloat("FloatJobValue");
        String triggerMsg = tdataMap.getString("message");
         double triggerDoubleValue = tdataMap.getDouble("DoubleTriggerValue");

        System.out.println("jobMsg is:" + jobMsg);
        System.out.println("jobFloatValue is:" + jobFloatValue);
        System.out.println("triggerMsg is:" + triggerMsg);
        System.out.println("triggerDoubleValue is:" +triggerDoubleValue);

    }
}