package com.noword.bean.message;

import java.sql.*;

/**
*
* <p>
* Title: DBConn
* </p>
* <p>
* Description:���ݿ�������
* </p>
* <p>
* Copyright: Copyright (c) 2008
* </p>
* <p>
* Company: 
* </p>
*
* @author EsonLiu
* @version 1.0
*/

public class DBConn {
	
	/**
	 * ���ݿ�����url,���ݿ���,���ݿ�����
	 */
	private static String dbUrl = "jdbc:mysql://localhost:3306/noword";
	private static String dbUserName = "root";
	private static String dbPassword = "123456";
	
	/**
	 * ������ݿ�����
	 * ��̬����,����ֱ�ӵ���,�����½������ٵ���
	 * ��̬�������õ��ķ�������ı���������Ϊ��̬����
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");  //����൱��new com.mysql.jdbc.Driver();
		}catch(Exception e) {
			System.out.println("�Ҳ������ݿ�������!");
			e.printStackTrace();
		}
		try{
			conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		}
		catch(Exception e) {
			System.out.println("��ȡ���ݿ����ӳ���!");
			e.printStackTrace();
		}
		return conn;
	}

}
