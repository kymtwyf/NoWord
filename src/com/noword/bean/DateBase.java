package com.noword.bean;
import java.sql.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class DateBase {
	
	public static Connection getConnection() throws SQLException, 
    java.lang.ClassNotFoundException  
    {
		//第一步：加载MySQL的JDBC的驱动
		Class.forName("com.mysql.jdbc.Driver");
		
		//取得连接的url,能访问MySQL数据库的用户名,密码；studentinfo：数据库名
		String url = "jdbc:mysql://localhost:3306/noword";        
		String username = "root";
		String password = "123456";
		
		//第二步：创建与MySQL数据库的连接类的实例
		Connection con = DriverManager.getConnection(url, username, password);        
		return con;        
	}
	
	@SuppressWarnings("null")
	public boolean insert(String _userName,String _code,String _email) 
	{
		try
        {      
            //第三步：获取连接类实例con，用con创建Statement对象类实例 sql_statement
			Connection con = getConnection();            
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement("select password from users where username = ?");
			pstmt.setString(1, _userName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				//用户名应经被使用
				 return false;
			}else{
				PreparedStatement pstmt2 = (PreparedStatement) con.prepareStatement("insert into users(username,password,email,sure) values (?,?,?,?)");
				pstmt2.setString(1,_userName);
				pstmt2.setString(2, _code);
				pstmt2.setString(3,_email);
				pstmt2.setInt(4, 0);
				pstmt2.executeUpdate();
			}
            pstmt.close();
            con.close();
            
        } catch(java.lang.ClassNotFoundException e) {
            //加载JDBC错误,所要用的驱动没有找到
            System.err.print("ClassNotFoundException");
            //其他错误
            System.err.println(e.getMessage());
        } catch (SQLException ex) {
            //显示数据库连接错误或查询错误
            System.err.println("SQLException: " + ex.getMessage());
        }
        return true;
	}
}
