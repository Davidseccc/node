package cz.uhk.secda1.node01.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

public class MySQLConnector {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public static final String ENC_PASSWORD = "KdhA4xXdcMmHLmp"; //salt to encript passwort to DB
        public static final String CONFIG_PATH = "/home/pi/"; //path to config file


    private void initDB() throws SQLException, ClassNotFoundException, IOException {
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

    public void readDataBase() throws Exception {

        try {
            initDB();

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            
            // Result set get the result of the SQL query
            preparedStatement = connect
                    .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
            // "myuser, webpage, datum, summery, COMMENTS from feedback.comments");
            // Parameters start with 1
            preparedStatement.setString(1, "Test");
            preparedStatement.setString(2, "TestEmail");
            preparedStatement.setString(3, "TestWebpage");
            preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
            preparedStatement.setString(5, "TestSummary");
            preparedStatement.setString(6, "TestComment");
            preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }
    
    // You need to close the resultSet
    private void close() {
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
