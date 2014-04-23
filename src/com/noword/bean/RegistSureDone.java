package com.noword.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
@ManagedBean
public class RegistSureDone {
	private String registSure;
	public String getRegistSure(){
		return registSure;
	}
	public void setRegistSure(String registSure){
		this.registSure = registSure;
	}
	public RegistSureDone() throws ClassNotFoundException, SQLException{
		FacesContext fc = FacesContext.getCurrentInstance();
		String username = (String) fc.getExternalContext().getRequestParameterMap().get("username");
		System.out.println(username);
		String url = "jdbc:mysql://localhost:3306/noword?user=root&password=123456";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url);
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("select sure from users where username = ?");
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			 if(rs.getInt("sure")==1){
				registSure= "亲爱的"+username+":您账户已处于激活状态==";
			 }else{
				 pstmt = (PreparedStatement) conn.prepareStatement("update users set sure=1 where username=?");
				 pstmt.setString(1, username);
				 pstmt.executeUpdate();
			 }
		}
		pstmt.close();
		conn.close();
		registSure= "亲爱的"+username+"账户激活成功！";
	}
}
