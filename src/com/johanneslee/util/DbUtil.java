package com.johanneslee.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class DbUtil {
	private static Connection conn = null;
	
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String PROPS_PATH = "db.properties";
    
    public DbUtil() {
		if(conn == null) {
			try {
				Properties props = new Properties();
				InputStream is = getClass().getResourceAsStream(PROPS_PATH);
				props.load(is);
				is.close();
				
				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				Class.forName(DRIVER);
				
				conn = DriverManager.getConnection(url, username, password);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Boolean isSelected(int turn, String number) {
		try {
			String str = "SELECT number FROM lottery WHERE turn=? AND number=?";
			PreparedStatement pstmt = conn.prepareStatement(str);
			pstmt.setInt(1, turn);
			pstmt.setString(2, number);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return false;
	}
	
	public void insertList(int turn, List<String> numbers) {
		Statement stmt = null;		
		String sql = "INSERT INTO lottery (turn, number, isBonus) VALUES";	
		
		for(int i = 0;i < numbers.size();i++) {
			if(isSelected(turn, numbers.get(i))) {
				return;
			}
			
			if(i == numbers.size() - 1) {
				sql = sql + "(" + turn + "," + numbers.get(i) + ",1)";
			} else {
				sql = sql + "(" + turn + "," + numbers.get(i) + ",0),";
			}
		}

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String drawNums() {
		JsonArray jsonArray = new JsonArray();
		
		try {
			String sql = "";
			sql = sql + "SELECT number, count(number) as count FROM lottery ";
			sql = sql + "GROUP BY number ORDER BY count(number) DESC ";
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int totRows  = rs.getMetaData().getColumnCount();
				JsonObject obj = new JsonObject();
				for(int i = 1;i <= totRows;i++) {
			        obj.addProperty(rs.getMetaData().getColumnLabel(i).toLowerCase(), rs.getString(i));
				}
				jsonArray.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonArray.toString();
	}
}