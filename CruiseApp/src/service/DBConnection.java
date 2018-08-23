package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;



public class DBConnection {
	
	private static Connection connection = null;

	 public static Connection getConnection() {
	        if (connection != null)
	            return connection;
	        else {
	            try {
	                Properties prop = new Properties();
	                InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("/db.properties");
	                prop.load(inputStream);
	                String driver = prop.getProperty("driver");
	                String url = prop.getProperty("url");
	                String user = prop.getProperty("user");
	                String password = prop.getProperty("password"); 
	                prop.setProperty("useUnicode","true");
	                prop.setProperty("characterEncoding","UTF-8");
	                Class.forName(driver);
	                connection = DriverManager.getConnection(url, prop);	                
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            } catch (FileNotFoundException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            return connection;
	        }

	    }
}
