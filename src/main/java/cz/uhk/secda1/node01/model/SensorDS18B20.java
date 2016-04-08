package cz.uhk.secda1.node01.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DS18B20 type temperature sensor.
 *
 * @author Marcus Hirt
 */
public class SensorDS18B20 implements ISensor {

    private final File sensorFile;
    private final File valueFile;
    private int SensorID;
    private Number value;
    

    public SensorDS18B20(String sensorFolder, int sensorID) {
        this.sensorFile = new File("/sys/devices/w1_bus_master1/" + sensorFolder);
        this.valueFile = deriveValueFile(sensorFile);
        this.SensorID = sensorID;
    }

    /**
     *
     */
    @Override
    public Number loadData() {
        Number val = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(
                valueFile))) {
            String tmp = reader.readLine();
            int index = -1;
            while (tmp != null) {
                index = tmp.indexOf("t=");
                if (index >= 0) {
                    break;
                }
                tmp = reader.readLine();
            }
            if (index < 0) {
                throw new IOException("Could not read sensor data");
            }
            val = Integer.parseInt(tmp.substring(index + 2)) / 1000f;
        } catch (IOException ex) {
            Logger.getLogger(SensorDS18B20.class.getName()).log(Level.SEVERE, null, ex);
        }
        setValue(val);
        return value;
    }

    public String getUnitString(){
        String valStr;
        try {
            double val = loadData().doubleValue();
            valStr = val + "°C";
        } catch (Exception e) {
            valStr = "Could not read sensor data of "+ sensorFile.getPath();
        }
        
        return valStr;
    }

    private static File deriveValueFile(File sensorFile) {
        return new File(sensorFile, "w1_slave");
    }

    public int getSensorID() {
        return SensorID;
    }

    public void setSensorID(int SensorID) {
        this.SensorID = SensorID;
    }

    public Number getValue() {
        return value;
    }

    public void setValue(Number value) {
        this.value = value;
    }
    
    
}
