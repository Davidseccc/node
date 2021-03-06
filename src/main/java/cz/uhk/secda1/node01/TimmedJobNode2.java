package cz.uhk.secda1.node01;

import cz.uhk.secda1.node01.model.CPU;
import cz.uhk.secda1.node01.model.DHT11;
import cz.uhk.secda1.node01.model.OpenWeatherMap;
import cz.uhk.secda1.node01.model.SensorDS18B20;
import cz.uhk.secda1.node01.service.ControllGpio;
import cz.uhk.secda1.node01.service.DAO.CPUDAO;
import cz.uhk.secda1.node01.service.DAO.SensorDHT11DAO;
import cz.uhk.secda1.node01.service.DAO.SensorDS18B20DAO;
import cz.uhk.secda1.node01.service.OpenWeatherMapParser;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TimmedJobNode2 implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            System.out.println("\n New Thread started:  " + dateFormat.format(cal.getTime()));

            execNode02();

        } catch (Exception ex) {
            Logger.getLogger(TimmedJobNode2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void execNode02() throws Exception {
        //NODE_02
        CPU cpu = new CPU(2);
        cpu.getCPUTemp();
        System.out.println("CPU Temp:" + cpu.getCPUTemp().toString() + "°C");
        CPUDAO cpudao = new CPUDAO();
        cpudao.insertValue(cpu);

        SensorDS18B20 s18B20 = new SensorDS18B20("28-000005e6f1c2", 8);
        System.out.println(s18B20.getUnitString());
        SensorDS18B20DAO s18B20DAO = new SensorDS18B20DAO();
        try {
            s18B20DAO.insertValue(s18B20);
        } catch (Exception ex) {
            Logger.getLogger(TimmedJobNode2.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        DHT11 sensor = new DHT11(5, 6, 22, 17);
        sensor.loadData();
        System.out.println("Done!! " + sensor.getUnitString() + " "
                + sensor.getHumidityString());
        SensorDHT11DAO sdhtdao = new SensorDHT11DAO();
        try {
            sdhtdao.insertValue(sensor);
        } catch (Exception ex) {
            Logger.getLogger(TimmedJobNode2.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

}
