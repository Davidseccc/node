package cz.uhk.secda1.node01.service.DAO;

import cz.uhk.secda1.node01.model.SensorDS18B20;

public class SensorDS18B20DAO {

      public void insertValue(SensorDS18B20 sensor) throws Exception {
          
        MySQLValueDAO valueDAO = new MySQLValueDAO();
        float value = sensor.getValue().floatValue();
        valueDAO.insertValue(value, sensor.getSensorID());
      
    }

}
