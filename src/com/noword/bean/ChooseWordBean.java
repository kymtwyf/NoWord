package com.noword.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped

public class ChooseWordBean {
	private String bookUrl;
	public String getBookUrl(){
		return bookUrl;
	}
	public void setBookUrl(String bookUrl){
		this.bookUrl = bookUrl;
	}
	public ChooseWordBean(){
		
	}
	public void linkDatabase(String table) throws ClassNotFoundException, SQLException{
		String url = "jdbc:mysql://localhost:3306/noword?user=root&password=123456";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url);
		PreparedStatement pstmt;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext extContext =facesContext.getExternalContext();
		HttpSession session =(HttpSession)extContext.getSession(true); 
		   //mes.setMesOperator(session.getAttribute("user").toString());
		String username = session.getAttribute("user").toString();
		pstmt = (PreparedStatement) conn.prepareStatement("update users set tablename=? where username=?");
		pstmt.setString(1,table);
		pstmt.setString(2,username);
		pstmt.executeUpdate();
		if(table.equals("cet6")){
			bookUrl = "pages/images/wordbookcover/cet6.jpg";
		}else if(table.equals("ielts")){
			bookUrl = "pages/images/wordbookcover/IELTS.jpg";
		}else{
			bookUrl = "pages/images/wordbookcover/TOEFL.jpg";
		}
		pstmt.close();
		conn.close();
	}
	public void chooseCet6() throws ClassNotFoundException, SQLException{
		linkDatabase("cet6");
	}
	public void chooseIelts() throws ClassNotFoundException, SQLException{
		linkDatabase("ielts");
	}
	public void chooseToefl() throws ClassNotFoundException, SQLException{
		linkDatabase("toefl");
	}
}
