package cz.uhk.secda1.node01.service.DAO;

import cz.uhk.secda1.node01.model.DHT11;

public class SensorDHT11DAO {

    
    public void insertValue(DHT11 sensor) throws Exception {
        MySQLValueDAO valueDAO = new MySQLValueDAO();
        
        valueDAO.insertValue(sensor.getTemperature().floatValue(), sensor.getTempSensorID());
        valueDAO.insertValue(sensor.getHumidity().floatValue(), sensor.getHumiditySensorID());
       

    }
   

}
