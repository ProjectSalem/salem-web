package com.project.salem.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericRepository {
	
	private Connection conn;
	private final String driver = "com.mysql.cj.jdbc.Driver";
	private final String db_username="root";
	private final String db_password="123456";
	private final String db_path = "jdbc:mysql://localhost:3306/salem";
	private final String db_params = "?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	public Connection getConnection() throws Exception {
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(db_path + db_params, db_username, db_password);
		return conn;
	}
	
	public void closeConnection() throws SQLException{
		conn.close();
	}

}
