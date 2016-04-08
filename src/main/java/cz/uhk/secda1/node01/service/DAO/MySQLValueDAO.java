package cz.uhk.secda1.node01.service.DAO;

import cz.uhk.secda1.node01.model.DHT11;
import cz.uhk.secda1.node01.model.SensorDS18B20;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

public class MySQLValueDAO {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public static final String ENC_PASSWORD = "KdhA4xXdcMmHLmp"; //salt to encript password to DB
        public static final String CONFIG_PATH = "/home/pi/"; //path to config file


    public void initDB() throws SQLException, ClassNotFoundException, IOException {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(ENC_PASSWORD);   
        URL res;
        Properties props = new EncryptableProperties(encryptor);
        props.load(new FileInputStream(CONFIG_PATH + "config.properties"));
        String urlString = props.getProperty("db.url");
        String driver = props.getProperty("db.driver");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        Class.forName(driver);
        connect = DriverManager
                .getConnection(urlString
                        + "?user=" + user + "&password=" + password);
    }

    public void insertValue(float value, int sensor_ID) throws Exception {

        try {
            initDB();

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            
            // Result set get the result of the SQL query
            preparedStatement = connect
                    .prepareStatement("CALL insertValuetoNode( ? , ? );");
            // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setFloat(1, value);
            preparedStatement.setInt(2, sensor_ID);
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    
    // You need to close the resultSet
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
