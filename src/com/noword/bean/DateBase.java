package com.noword.bean;
import java.sql.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class DateBase {
	
	public static Connection getConnection() throws SQLException, 
    java.lang.ClassNotFoundException  
    {
		//��һ��������MySQL��JDBC������
		Class.forName("com.mysql.jdbc.Driver");
		
		//ȡ�����ӵ�url,�ܷ���MySQL���ݿ���û���,���룻studentinfo�����ݿ���
		String url = "jdbc:mysql://localhost:3306/noword";        
		String username = "root";
		String password = "123456";
		
		//�ڶ�����������MySQL���ݿ���������ʵ��
		Connection con = DriverManager.getConnection(url, username, password);        
		return con;        
	}
	
	@SuppressWarnings("null")
	public boolean insert(String _userName,String _code,String _email) 
	{
		try
        {      
            //����������ȡ������ʵ��con����con����Statement������ʵ�� sql_statement
			Connection con = getConnection();            
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement("select password from users where username = ?");
			pstmt.setString(1, _userName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
			{
				//�û���Ӧ����ʹ��
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
            //����JDBC����,��Ҫ�õ�����û���ҵ�
            System.err.print("ClassNotFoundException");
            //��������
            System.err.println(e.getMessage());
        } catch (SQLException ex) {
            //��ʾ���ݿ����Ӵ�����ѯ����
            System.err.println("SQLException: " + ex.getMessage());
        }
        return true;
	}
}
