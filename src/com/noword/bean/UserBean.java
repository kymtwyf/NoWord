package com.noword.bean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class UserBean {
	private String username;
	private String password;
	private String tablename;
	private String oldpassword;
	private String code1;
	private String code2;
	private String nickname;
	private String headportrait;
	private String studymodel;
	private String wordamount;
	private String soundmodel;
	private String englishtrans;
	private String level;

    private List<String> images;
	private HttpSession session;  
    
    @PostConstruct  
    public void init() {  
        images = new ArrayList<String>();  
        for(int i=1;i<=3;i++) {  
            images.add("damn" + i + ".jpg");  
        }  
    }   
    public List<String> getImages() {  
        return images;  
    }  
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public String getTablename(){
		return tablename;
	}
	public void setTablename(String tablename){
		this.tablename = tablename;
	}
	public String getOldpassword(){
		return oldpassword;
	}
	public void setOldpassword(String oldpassword){
		this.oldpassword = oldpassword;
	}
	public String getCode1(){
		return code1;
	}
	public void setCode1(String code1){
		this.code1 = code1;
	}
	public String getCode2(){
		return code2;
	}
	public void setCode2(String code2){
		this.code2 = code2;
	}
	public String getNickname(){
		return nickname;
	}
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	public String getStudymodel(){
		return studymodel;
	}
	public void setStudymodel(String studymodel){
		this.studymodel = studymodel;
	}
	
	public String getWordamount(){
		return wordamount;
	}
	public void setWordamount(String wordamount){
		this.wordamount = wordamount;
	}
	public String getEnglishtrans(){
		return englishtrans;
	}
	public void setEnglishtrans(String englishtrans){
		this.englishtrans = englishtrans;
	}
	public String getLevel(){
		return level;
	}
	public void setLevel(String level){
		this.level = level;
	}
	public String getSoundmodel(){
		return soundmodel;
	}
	public void setSoundmodel(String soundmodel){
		this.soundmodel = soundmodel;
	}
	
	public String getHeadportrait()throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		try{
			String url = "jdbc:mysql://localhost:3306/noword";        
			String username = "root";
			String password = "123456";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password); 
			Statement sql_statement = conn.createStatement();
			ResultSet result = sql_statement.executeQuery("select headportrait from users where username = '"+this.username+"'");
			while(result.next())
				headportrait = result.getString("headportrait");
			sql_statement.close();
	        conn.close();
	            
	        } catch(java.lang.ClassNotFoundException e) {
	            //加载JDBC错误,所要用的驱动没有找到
	            System.err.print("ClassNotFoundException");
	            //其他错误
	            System.err.println(e.getMessage());
	        } catch (SQLException ex) {
	            //显示数据库连接错误或查询错误
	            System.err.println("SQLException: " + ex.getMessage());
	        }
		return headportrait;
	}
	public void setHeadportrait(String headportrait){
		this.headportrait = headportrait;
	}
	
	public String studyModel()throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		try{
			String url = "jdbc:mysql://localhost:3306/noword";        
			String username = "root";
			String password = "123456";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password); 
			Statement sql_statement = conn.createStatement();
			sql_statement.executeUpdate("update studyplan set studymodel = '"+this.studymodel+"'  where username = '"+this.username+"'");
			sql_statement.executeUpdate("update studyplan set dayamount = '"+this.wordamount+"'  where username = '"+this.username+"'");
			sql_statement.close();
	        conn.close();
	            
	        } catch(java.lang.ClassNotFoundException e) {
	            //加载JDBC错误,所要用的驱动没有找到
	            System.err.print("ClassNotFoundException");
	            //其他错误
	            System.err.println(e.getMessage());
	        } catch (SQLException ex) {
	            //显示数据库连接错误或查询错误
	            System.err.println("SQLException: " + ex.getMessage());
	        }
			return "userinfo?faces-redirect=true&username="+username;
	}
	
	public String wordSet()throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		try{
			String url = "jdbc:mysql://localhost:3306/noword";        
			String username = "root";
			String password = "123456";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password); 
			Statement sql_statement = conn.createStatement();
			sql_statement.executeUpdate("update wordset set soundmodel = '"+this.soundmodel+"'  where username = '"+this.username+"'");
			sql_statement.executeUpdate("update wordset set showenglishtrans = '"+this.englishtrans+"'  where username = '"+this.username+"'");
			sql_statement.executeUpdate("update wordset set levelattained = '"+this.level+"'  where username = '"+this.username+"'");
			sql_statement.close();
	        conn.close();
	            
	        } catch(java.lang.ClassNotFoundException e) {
	            //加载JDBC错误,所要用的驱动没有找到
	            System.err.print("ClassNotFoundException");
	            //其他错误
	            System.err.println(e.getMessage());
	        } catch (SQLException ex) {
	            //显示数据库连接错误或查询错误
	            System.err.println("SQLException: " + ex.getMessage());
	        }
			return "userinfo?faces-redirect=true&username="+username;
	}
	
	public String editNickname()throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		try{
			String url = "jdbc:mysql://localhost:3306/noword";        
			String username = "root";
			String password = "123456";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password); 
			Statement sql_statement = conn.createStatement();
			sql_statement.executeUpdate("update users set nickname = '"+this.nickname+"'  where username = '"+this.username+"'");
			sql_statement.close();
	        conn.close();
	            
	        } catch(java.lang.ClassNotFoundException e) {
	            //加载JDBC错误,所要用的驱动没有找到
	            System.err.print("ClassNotFoundException");
	            //其他错误
	            System.err.println(e.getMessage());
	        } catch (SQLException ex) {
	            //显示数据库连接错误或查询错误
	            System.err.println("SQLException: " + ex.getMessage());
	        }
			return "userinfo?faces-redirect=true&username="+username;
	}
	public String changePassword()throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		if(!(this.password).equals(this.oldpassword)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"密码不正确","你输入的密码不正确！"));
			return "changeCode?faces-redirect=true&username="+username;
		}
		if(!this.code1.endsWith(code2))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"新密码不正确","两次输入的密码不一致！"));
			return "changeCode?faces-redirect=true&username="+username;
		}
		else
		{
			try{
			String url = "jdbc:mysql://localhost:3306/noword";        
			String username = "root";
			String password = "123456";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password); 
			Statement sql_statement = conn.createStatement();
			sql_statement.executeUpdate("update users set password = '"+this.code1+"'  where username = '"+this.username+"'");
			sql_statement.close();
	        conn.close();
	            
	        } catch(java.lang.ClassNotFoundException e) {
	            //加载JDBC错误,所要用的驱动没有找到
	            System.err.print("ClassNotFoundException");
	            //其他错误
	            System.err.println(e.getMessage());
	        } catch (SQLException ex) {
	            //显示数据库连接错误或查询错误
	            System.err.println("SQLException: " + ex.getMessage());
	        }
			return "userinfo?faces-redirect=true&username="+username;
		}
	}
	public String checkUser() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		String url = "jdbc:mysql://localhost:3306/noword?user=root&password=123456";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url);
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("select password,sure,tablename from users where username = ?");
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext extContext =facesContext.getExternalContext();
		this.session =(HttpSession)extContext.getSession(true);
		this.session.setAttribute("user", username);
		
		 if(rs.next()){
			 if(rs.getString("password").equals(this.password)){
				 if(rs.getInt("sure")==1){
					 System.out.println("111");
					 	if(rs.getString("tablename").equals("none")){
					 		 System.out.println("222");
					 		return "changebook?faces-redirect=true";
					 	}else{
					 		 System.out.println("333");
					 		return "wordPage?faces-redirect=true&username="+username;
					 	}
				 }else{
					 return "successRegist?faces-redirect=true&username="+username;
				 }
			 }else{
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"密码不正确","你输入的密码不正确！"));
				 return null;
			 }
		 }
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"用户不存在","你输入的用户名不存在！"));
		 return null;
		 
	}
	public String loginOut() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext extContext =facesContext.getExternalContext();
		this.session =(HttpSession)extContext.getSession(true);
		this.session.invalidate();
		return "welcomePage?faces-redirect=true";
	}
	public String toPerson() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		return "myspace?faces-redirect=true&username="+username;
	}
	public String toMySpace() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		return "userinfo?faces-redirect=true&username="+username;
	}
	public String toMyWord() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		return "wordPage?faces-redirect=true&username="+username;
	}
	public String toHomePage() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		return "welcomePage?faces-redirect=true&username="+username;
	}

}
