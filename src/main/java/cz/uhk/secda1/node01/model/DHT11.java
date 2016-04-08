package cz.uhk.secda1.node01.model;

import java.util.List;
import java.util.ArrayList;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DHT11 implements ISensor{

    private Number temperature;
    private Number humidity;
    private int tempSensorID;
   private int humiditySensorID;


    private int[] dht11_dat = {0, 0, 0, 0, 0};

    public DHT11() {
    }
    
      public DHT11(int tempSensorID, int humiditySensorID) {
          this.tempSensorID = tempSensorID;
          this.humiditySensorID = humiditySensorID;
    }

    @Override
    public Number loadData() {
  String cmd = "sudo python /home/pi/AdafruitDHT.py 11 17";
        String ret = "";
        String output = "";

        try {
            String line;
            Process p = Runtime.getRuntime().exec(cmd.split(" "));
            p.waitFor();
            try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
                while ((line = input.readLine()) != null) {
                    output += (line + '\n');
                }
            }
            System.out.println(output);

        } catch (IOException | InterruptedException e) {
        }    


    parseValue(output);
    return this.temperature;
    }


    public void parseValue(String ret) {
        //ret.trim();
        if (ret.length() == 0) // Library is not present
        {
            throw new RuntimeException("LIB_NOT_PRESENT_MESSAGE");
        }  // Error reading the the sensor, maybe is not connected. 
        // Read completed. Parse and update the values
        String[] vals = ret.split("   ");
        setTemperature( Float.parseFloat(vals[0].trim()));
        setHumidity( Float.parseFloat(vals[1].trim()));

    }
    
    public String getUnitString(){
        return getTemperature() + "°C";
    }
    
     public String getHumidityString(){
        return getHumidity() + "%";
    }


    public Number getHumidity() {
        return humidity;
    }

    public void setHumidity(Number humidity) {
        this.humidity = humidity;
    }

    public Number getTemperature() {
        return temperature;
    }

    public void setTemperature(Number temperature) {
        this.temperature = temperature;
    }

    public int getTempSensorID() {
        return tempSensorID;
    }

    public void setTempSensorID(int tempSensorID) {
        this.tempSensorID = tempSensorID;
    }

    public int getHumiditySensorID() {
        return humiditySensorID;
    }

    public void setHumiditySensorID(int humiditySensorID) {
        this.humiditySensorID = humiditySensorID;
    }
    
    
    
}
