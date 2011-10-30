package ece651.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {

	public Connection openConnection(){
		Properties prop = new Properties();
		String driver = null;
		String url = null;
		String username = null;
		String password =  null;
		
		try{
			prop.load(this.getClass().getClassLoader().getResourceAsStream("DBConfig.properties"));
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		}
		catch( Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeConnection( Connection conn) {
		try {
			conn.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
}
