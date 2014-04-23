package com.noword.bean.message;

import java.sql.*;

/**
*
* <p>
* Title: DBConn
* </p>
* <p>
* Description:数据库连接类
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
	 * 数据库连接url,数据库名,数据库密码
	 */
	private static String dbUrl = "jdbc:mysql://localhost:3306/noword";
	private static String dbUserName = "root";
	private static String dbPassword = "123456";
	
	/**
	 * 获得数据库连接
	 * 静态方法,由类直接调用,不用新建对象再调用
	 * 静态方法里用到的方法外面的变量都必须为静态变量
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");  //这句相当于new com.mysql.jdbc.Driver();
		}catch(Exception e) {
			System.out.println("找不到数据库驱动类!");
			e.printStackTrace();
		}
		try{
			conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		}
		catch(Exception e) {
			System.out.println("获取数据库连接出错!");
			e.printStackTrace();
		}
		return conn;
	}

}
