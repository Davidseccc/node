package cz.uhk.secda1.node01;

import cz.uhk.secda1.node01.service.SocketServer;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Node main runnable class.
 *
 * @author Šec David
 */

public class Main {
        
    public static final int PORT = 9999;

    public static void main(String[] args) throws Exception {

        (new Thread(new SocketServer(PORT, SocketServer.TIMEOUT_NEWER))).start();

        JobDetail job = JobBuilder.newJob(TimmedJobNode1.class)   //Specify node timed job...
                .withIdentity("Job", "Measurement").build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("Trigger", "Measurement")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0 0/10 * * * ? *")) // every 10 minutes
                        //CronScheduleBuilder.cronSchedule("0/20 * * 1/1 * ? *")) // every 20 minutes
                        //CronScheduleBuilder.cronSchedule("0 0/1 * * * ? *")) // every minute
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);

    }

   
}
