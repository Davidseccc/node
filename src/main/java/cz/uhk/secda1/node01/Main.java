package cz.uhk.secda1.node01;

import cz.uhk.secda1.node01.service.SocketServer;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Main {
    
    public static final int PORT = 9999;

    public static void main(String[] args) throws Exception {

        (new Thread(new SocketServer(PORT, SocketServer.TIMEOUT_NEWER))).start();

        JobDetail job = JobBuilder.newJob(TimmedJob.class)
                .withIdentity("MyJob", "group1").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("MyTrigger", "group1")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0/10 * * 1/1 * ? *"))
                        //CronScheduleBuilder.cronSchedule("0 0/10 * * * ? *"))
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);

    }

   
}
