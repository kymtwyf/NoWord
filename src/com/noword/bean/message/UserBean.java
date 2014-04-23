package com.noword.bean.message;

import java.sql.*;
import java.util.*;


public class UserBean {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	/**
	 * 根据用户名及密码查找User并返回User对象
	 * @param username
	 * @param password
	 * @return
	 */
	public User checkUser(String username, String password) {
		//获得数据库连接对象
		conn = DBConn.getConnection();
		//查询用户名和密码分别与传进来的参数相同的用户的SQL语句
		String sqlQuery = "select * from t_user u where u.username='" + username + "' and u.password='" + password + "'";
		
		User user = null;
		
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlQuery); 
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setUserType(rs.getInt("userType"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
		//返回User对象
		return user;
	}
	
	
	/**
	 * 关闭数据库资源的通用方法
	 */
	private void closeDB() {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
