package com.project.salem.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class IndexRepository extends GenericRepository {
	
	public boolean authenticateUser(String username, String password){
	
		boolean auth = false;
		PreparedStatement st;
		
		try {
			Connection conn = getConnection();
			st = conn.prepareStatement("SELECT COUNT(*) > 0 AS FOUND FROM user WHERE username = ? AND password = MD5(?)");
			st.setString(1, username);
			st.setString(2, password);
			
			ResultSet rs = st.executeQuery();
			
			if(rs.next())
				auth = rs.getBoolean(1);
			
			conn.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
		return auth;
	}	
}
