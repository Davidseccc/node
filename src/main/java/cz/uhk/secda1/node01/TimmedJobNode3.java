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

public class TimmedJobNode3 implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            System.out.println("\n New Thread started:  " + dateFormat.format(cal.getTime()));

            execNode03();

        } catch (Exception ex) {
            Logger.getLogger(TimmedJobNode3.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void execNode03() throws Exception {
        //NODE_03
        CPU cpu = new CPU(3);
        cpu.getCPUTemp();
        System.out.println("CPU Temp:" + cpu.getCPUTemp().toString() + "°C");
        CPUDAO cpudao = new CPUDAO();
        cpudao.insertValue(cpu);

        OpenWeatherMapParser weatherParser = new OpenWeatherMapParser();
        OpenWeatherMap wm = weatherParser.parse();
        System.out.println(wm.toString());
        ControllGpio windowRelay = new ControllGpio();

        if (wm.canOpenWindow()) {
            windowRelay.switchOn();
        } else {
            windowRelay.switchOff();

        }
    }

}
