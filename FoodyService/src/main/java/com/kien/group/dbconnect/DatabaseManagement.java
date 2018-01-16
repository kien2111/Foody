package com.kien.group.dbconnect;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import com.mysql.jdbc.Connection;
public class DatabaseManagement {
	static DatabaseManagement databasemanagement;
	private String url;

	public String getUrl() {
		return url;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getDriver() {
		return driver;
	}
	public String getResourceurl() {
		return resourceurl;
	}
	private String database;

	private String username;

	private String password;
	
	private String driver;
	
	private String resourceurl; 

	protected Connection connection;
	private DatabaseManagement(){
		getProperties();
		connection = getConnection();
	}
	public static synchronized DatabaseManagement getInstance(){
		if(databasemanagement!=null){
			return databasemanagement;
		}else{
			return new DatabaseManagement();
		}
	}
	public Connection getConnection(){
		
		try {
			return (Connection) DriverManager
			        .getConnection(url + database+"?useUnicode=true&characterEncoding=UTF-8", username, password);
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return null;
	}
	private void getProperties(){
		Properties prop = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		
		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver = prop.getProperty("jdbc.driver").toString();
        url = prop.getProperty("jdbc.url").toString();
        database = prop.getProperty("jdbc.database").toString();
        username = prop.getProperty("jdbc.user").toString();
        password = prop.getProperty("jdbc.password").toString();
        resourceurl = prop.getProperty("jdbc.resourceurl").toString();
        try {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Class.forName(driver).newInstance();
		} catch (Exception e) {		
			e.printStackTrace();
		}
	}
	
	
}
