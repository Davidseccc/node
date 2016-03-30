package cz.uhk.secda1.node01;

import cz.uhk.secda1.node01.model.DHT11;
import cz.uhk.secda1.node01.model.OpenWeatherMap;
import cz.uhk.secda1.node01.service.OpenWeatherMapParser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TimmedJob implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        System.out.println("Hello Quartz!   " + dateFormat.format(cal.getTime()));

        //SensorDS18B20 s18B20 = new SensorDS18B20("28-000005e526d7");
        //             System.out.println(s18B20.getUnitString());
        
        DHT11 sensor = new DHT11();
        sensor.loadData();
        System.out.println("Done!! "  + sensor.getUnitString() +" " +sensor.getHumidityString());

        OpenWeatherMapParser weatherParser = new OpenWeatherMapParser();
        OpenWeatherMap wm = weatherParser.parse();

    }
}
