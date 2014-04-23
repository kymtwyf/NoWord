package com.noword.bean.message;

import java.sql.*;
import java.util.*;


public class UserBean {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	/**
	 * �����û������������User������User����
	 * @param username
	 * @param password
	 * @return
	 */
	public User checkUser(String username, String password) {
		//������ݿ����Ӷ���
		conn = DBConn.getConnection();
		//��ѯ�û���������ֱ��봫�����Ĳ�����ͬ���û���SQL���
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
		
		//����User����
		return user;
	}
	
	
	/**
	 * �ر����ݿ���Դ��ͨ�÷���
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
