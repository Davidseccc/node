package cz.uhk.secda1.node01.model;

import java.util.List;
import java.util.ArrayList;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * DHT11 temperature and humidity sensor.
 *
 * @author Šec David
 */

public class DHT11 implements ISensor {

    private Number temperature;
    private Number humidity;
    private int tempSensorID;
    private int humiditySensorID;
    private int sensor;
    private int gpioPin;

    private int[] dht11_dat = {0, 0, 0, 0, 0};

    public DHT11() {

    }

    public DHT11(int tempSensorID, int humiditySensorID, int sensor, int gpioPin) {
        this.tempSensorID = tempSensorID;
        this.humiditySensorID = humiditySensorID;
        this.sensor = sensor;
        this.gpioPin = gpioPin;
    }

    /**
     * DHT11 temperature and humidity sensor. Load data from sensor using python
     * script which return two values. return Number value [in °C]; sensor : 11
     * for DTH 11 or 22 for DHT22 sensor gpinPin : GPIO pin in which is sensor
     * connected (use GPIO number no pin number)
     */
    @Override
    public Number loadData() {
        String cmd = "sudo python /home/pi/AdafruitDHT.py " + sensor + " " + gpioPin;
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

    /**
     * Split and trim outut string from loadData() to two Numbers – Temaperature
     * and Humidity. First value is temperature in °C, second humidity in %.
     * Trim string is " " (three spaces) Could be changed in python script.
     * @param ret String with humidity ad temperature
     */
    public void parseValue(String ret) {
        //ret.trim();
        if (ret.length() == 0) // Library is not present
        {
            throw new RuntimeException("LIB_NOT_PRESENT_MESSAGE");
        }  // Error reading the the sensor, maybe is not connected. 
        // Read completed. Parse and update the values
        String[] vals = ret.split("   ");
        setTemperature(Float.parseFloat(vals[0].trim()));
        setHumidity(Float.parseFloat(vals[1].trim()));

    }

    public String getUnitString() {
        return getTemperature() + "°C";
    }

    public String getHumidityString() {
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
