package com.bb2004.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC { 
	 /**
	  * 1.加载驱动
	  * 2.驱动管理类创建连接.
	  * 3.通过连接创建声明SQL.
	  * 4.executeQurey(); executeUpdate();
	  */
	 
	public static Connection getConnection(){
		Properties pro = new Properties();
		try {
			pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
		}catch (IOException e1) {
			e1.printStackTrace();
		}
		String url = pro.getProperty("jdbc.mysql.url");
		String user = pro.getProperty("jdbc.mysql.username");
		String pwd = pro.getProperty("jdbc.mysql.password");
		String driver = pro.getProperty("jdbc.mysql.driver");
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
