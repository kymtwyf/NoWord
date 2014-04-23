package com.noword.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class WordBean {
	private int word_id;
	private String word;
	private String mean;
    private String en_sentence;
	private String ch_sentence;
	private String table_name;
	private String p_uk;
	private String p_usa;
	private String hx1;
	private String hx2;
	private String hx3;
	private String hx4;
	private String flashvars1;
	private String flashvars2;
	private int degree;
	private String degreeUrl;
	
	public WordBean() throws ClassNotFoundException, SQLException{
		word_id = 1;
		System.out.println(word_id);
		String url = "jdbc:mysql://localhost:3306/noword?user=root&password=123456";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext extContext =facesContext.getExternalContext();
		HttpSession session =(HttpSession)extContext.getSession(true); 
		   //mes.setMesOperator(session.getAttribute("user").toString());
		String username = session.getAttribute("user").toString();
		PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("select tablename from users where username=?");
		pstmt.setString(1,username);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			table_name = rs.getString("tablename");
		}
		session.setAttribute("table", table_name);
		rs.close();
		pstmt.close();
		conn.close();
		System.out.println(table_name);
		try {
			getNextWord();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getWord_id(){
		return word_id;
	}
	public void setWord_id(int word_id){
		this.word_id = word_id;
	}
	public String getWord(){
		return word;
	}
	public void setWord(String word){
		this.word = word;
	}
	public String getMean(){
		return mean;
	}
	public void setMean(String mean){
		this.mean = mean;
	}
	public String getEn_sentence(){
		return en_sentence;
	}
	public void setEn_sentence(String en_sentence){
		this.en_sentence = en_sentence;
	}
	public String getCh_sentence(){
		return ch_sentence;
	}
	public void setCh_sentence(String ch_sentence){
		this.ch_sentence = ch_sentence;
	}
	public void setP_uk(String p_uk){
		this.p_uk  = p_uk;
	}
	public String getP_uk(){
		return p_uk;
	}
	public void setP_usa(String p_usa){
		this.p_usa  = p_usa;
	}
	public String getP_usa(){
		return p_usa;
	}
	public void setHx1(String hx1){
		this.hx1  = hx1;
	}
	public String getHx1(){
		return hx1;
	}
	public void setHx2(String hx2){
		this.hx2  = hx2;
	}
	public String getHx2(){
		return hx2;
	}
	public void setHx3(String hx3){
		this.hx3  = hx3;
	}
	public String getHx3(){
		return hx3;
	}
	public void setHx4(String hx4){
		this.hx4  = hx4;
	}
	public String getHx4(){
		return hx4;
	}
	public void setFlashvars1(String flashvars1){
		this.flashvars1  = flashvars1;
	}
	public String getFlashvars1(){
		return flashvars1;
	}
	public void setFlashvars2(String flashvars2){
		this.flashvars2  = flashvars2;
	}
	public String getFlashvars2(){
		return flashvars2;
	}
	
	public void setDegreeUrl(String degreeUrl){
		this.degreeUrl=degreeUrl;
	}
	
	public String getDegreeUrl(){
		return degreeUrl;
	}
	public void getNextWord() throws ClassNotFoundException, SQLException{
		System.out.print(word_id);
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext extContext =facesContext.getExternalContext();
		HttpSession session =(HttpSession)extContext.getSession(true); 
		   //mes.setMesOperator(session.getAttribute("user").toString());
		String tablename= session.getAttribute("table").toString();
		String name = session.getAttribute("user").toString();
		System.out.println(tablename);
		String url = "jdbc:mysql://localhost:3306/noword?user=root&password=123456";
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url);
		PreparedStatement pstmt;
		if(tablename.equals("cet6")){
			pstmt = (PreparedStatement) conn.prepareStatement("select * from cet6 where word_id=?");
			pstmt.setInt(1,word_id);
		}else if(tablename.equals("ielts")){
			pstmt = (PreparedStatement) conn.prepareStatement("select * from ielts where word_id=?");
			pstmt.setInt(1,word_id);
		}else{
			pstmt = (PreparedStatement) conn.prepareStatement("select * from toefl where word_id=?");
			pstmt.setInt(1,word_id);
		}
		System.out.println(tablename);
		//pstmt.setString(1,tablename);
		//pstmt.setString(1,tablename);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			word = rs.getString("word");
			mean = rs.getString("mean");
			en_sentence = rs.getString("en_sentence");
			ch_sentence = rs.getString("ch_sentence");
			p_uk = rs.getString("p_uk");
			p_usa = rs.getString("p_usa");
			hx1 = rs.getString("hx1");
			hx2 = rs.getString("hx2");
			hx3 = rs.getString("hx3");
			pstmt = (PreparedStatement) conn.prepareStatement("select * from cet6_user where word_id=? and username=?");
			pstmt.setInt(1,word_id);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			if(rs.next()){
				degree = rs.getInt("degree");
				System.out.println(degree);
			}else{
				degree = 0;
			}
			flashvars1 =  "url="+p_uk+"&amp;auto=1";
			flashvars2 = "url="+p_usa+"&amp;auto=0";
			degreeUrl = "http://116.213.69.56/commons/css/images/baifenbi"+degree+".jpg";
			System.out.println(degreeUrl);
			String a[] = new String[4];
			String b[] = new String[4];
			boolean picked[] = new boolean[4];
			Random rand = new Random();
			a[0] = hx1;
			a[1] = hx2;
			a[2] = hx3;
			a[3] = word;
			for(int i = 0;i<4;i++){
				int t;
	            do
	                t = rand.nextInt(a.length);
	            while(picked[t]);
	                b[i]=a[t];
	                picked[t] =true;
			}
			hx1= b[0];
			hx2= b[1];
			hx3= b[2];
			hx4= b[3];
			System.out.println(hx1);
			System.out.println(hx2);
			System.out.println(hx3);
			System.out.println(hx4);
		}
		word_id++;
		return;
	}
	public void checkHx1() throws ClassNotFoundException, SQLException{
		if(hx1==word){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext extContext =facesContext.getExternalContext();
			HttpSession session =(HttpSession)extContext.getSession(true); 
			   //mes.setMesOperator(session.getAttribute("user").toString());
			String tablename= session.getAttribute("table").toString();
			String name = session.getAttribute("user").toString();
			String url = "jdbc:mysql://localhost:3306/noword?user=root&password=123456";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement pstmt;
			if(tablename.equals("cet6")){
				pstmt = (PreparedStatement) conn.prepareStatement("select * from cet6_user where word_id=? and username=?");
				pstmt.setInt(1,word_id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					degree = rs.getInt("degree");
					degree++;
					pstmt = (PreparedStatement) conn.prepareStatement("update cet6_user set degree=? where username=? and word_id=?");
					pstmt.setInt(1,degree);
					pstmt.setString(2,name);
					pstmt.setInt(3,word_id);
					pstmt.executeUpdate();
				}else{
					pstmt = (PreparedStatement) conn.prepareStatement("insert into cet6_user(username,word_id,degree) values (?,?,1)");
					pstmt.setString(1,name);
					pstmt.setInt(2,word_id);
					pstmt.executeUpdate();
				}
			}else if(tablename.equals("ielts")){
				pstmt = (PreparedStatement) conn.prepareStatement("select * from ielts_user where word_id=? and username=?");
				pstmt.setInt(1,word_id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					degree = rs.getInt("degree");
					degree++;
					pstmt = (PreparedStatement) conn.prepareStatement("update ielts_user set degree=? where username=? and word_id=?");
					pstmt.setInt(1,degree);
					pstmt.setString(2,name);
					pstmt.setInt(3,word_id);
					pstmt.executeUpdate();
				}else{
					pstmt = (PreparedStatement) conn.prepareStatement("insert into ielts_user(username,word_id,degree) values (?,?,1)");
					pstmt.setString(1,name);
					pstmt.setInt(2,word_id);
					pstmt.executeUpdate();
				}
			}else{
				pstmt = (PreparedStatement) conn.prepareStatement("select * from toefl_user where word_id=?and username=?");
				pstmt.setInt(1,word_id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					degree = rs.getInt("degree");
					degree++;
					pstmt = (PreparedStatement) conn.prepareStatement("update toefl_user set degree=? where username=? and word_id=?");
					pstmt.setInt(1,degree);
					pstmt.setString(2,name);
					pstmt.setInt(3,word_id);
					pstmt.executeUpdate();
				}else{
					pstmt = (PreparedStatement) conn.prepareStatement("insert into toefl_user(username,word_id,degree) values (?,?,1)");
					pstmt.setString(1,name);
					pstmt.setInt(2,word_id);
					pstmt.executeUpdate();
				}
			}
			pstmt.close();
			conn.close();
		}
	}
	
	public void checkHx2() throws SQLException, ClassNotFoundException{
		if(hx2==word){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext extContext =facesContext.getExternalContext();
			HttpSession session =(HttpSession)extContext.getSession(true); 
			   //mes.setMesOperator(session.getAttribute("user").toString());
			String tablename= session.getAttribute("table").toString();
			String name = session.getAttribute("user").toString();
			String url = "jdbc:mysql://localhost:3306/noword?user=root&password=123456";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement pstmt;
			if(tablename.equals("cet6")){
				pstmt = (PreparedStatement) conn.prepareStatement("select * from cet6_user where word_id=? and username=?");
				pstmt.setInt(1,word_id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					degree = rs.getInt("degree");
					degree++;
					pstmt = (PreparedStatement) conn.prepareStatement("update cet6_user set degree=? where username=? and word_id=?");
					pstmt.setInt(1,degree);
					pstmt.setString(2,name);
					pstmt.setInt(3,word_id);
					pstmt.executeUpdate();
				}else{
					pstmt = (PreparedStatement) conn.prepareStatement("insert into cet6_user(username,word_id,degree) values (?,?,1)");
					pstmt.setString(1,name);
					pstmt.setInt(2,word_id);
					pstmt.executeUpdate();
				}
			}else if(tablename.equals("ielts")){
				pstmt = (PreparedStatement) conn.prepareStatement("select * from ielts_user where word_id=? and username=?");
				pstmt.setInt(1,word_id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					degree = rs.getInt("degree");
					degree++;
					pstmt = (PreparedStatement) conn.prepareStatement("update ielts_user set degree=? where username=? and word_id=?");
					pstmt.setInt(1,degree);
					pstmt.setString(2,name);
					pstmt.setInt(3,word_id);
					pstmt.executeUpdate();
				}else{
					pstmt = (PreparedStatement) conn.prepareStatement("insert into ielts_user(username,word_id,degree) values (?,?,1)");
					pstmt.setString(1,name);
					pstmt.setInt(2,word_id);
					pstmt.executeUpdate();
				}
			}else{
				pstmt = (PreparedStatement) conn.prepareStatement("select * from toefl_user where word_id=?and username=?");
				pstmt.setInt(1,word_id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					degree = rs.getInt("degree");
					degree++;
					pstmt = (PreparedStatement) conn.prepareStatement("update toefl_user set degree=? where username=? and word_id=?");
					pstmt.setInt(1,degree);
					pstmt.setString(2,name);
					pstmt.setInt(3,word_id);
					pstmt.executeUpdate();
				}else{
					pstmt = (PreparedStatement) conn.prepareStatement("insert into toefl_user(username,word_id,degree) values (?,?,1)");
					pstmt.setString(1,name);
					pstmt.setInt(2,word_id);
					pstmt.executeUpdate();
				}
			}
			pstmt.close();
			conn.close();
		}
	}
	
	public void checkHx3() throws ClassNotFoundException, SQLException{
		if(hx3==word){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext extContext =facesContext.getExternalContext();
			HttpSession session =(HttpSession)extContext.getSession(true); 
			   //mes.setMesOperator(session.getAttribute("user").toString());
			String tablename= session.getAttribute("table").toString();
			String name = session.getAttribute("user").toString();
			String url = "jdbc:mysql://localhost:3306/noword?user=root&password=123456";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement pstmt;
			if(tablename.equals("cet6")){
				pstmt = (PreparedStatement) conn.prepareStatement("select * from cet6_user where word_id=? and username=?");
				pstmt.setInt(1,word_id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					degree = rs.getInt("degree");
					degree++;
					pstmt = (PreparedStatement) conn.prepareStatement("update cet6_user set degree=? where username=? and word_id=?");
					pstmt.setInt(1,degree);
					pstmt.setString(2,name);
					pstmt.setInt(3,word_id);
					pstmt.executeUpdate();
				}else{
					pstmt = (PreparedStatement) conn.prepareStatement("insert into cet6_user(username,word_id,degree) values (?,?,1)");
					pstmt.setString(1,name);
					pstmt.setInt(2,word_id);
					pstmt.executeUpdate();
				}
			}else if(tablename.equals("ielts")){
				pstmt = (PreparedStatement) conn.prepareStatement("select * from ielts_user where word_id=? and username=?");
				pstmt.setInt(1,word_id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					degree = rs.getInt("degree");
					degree++;
					pstmt = (PreparedStatement) conn.prepareStatement("update ielts_user set degree=? where username=? and word_id=?");
					pstmt.setInt(1,degree);
					pstmt.setString(2,name);
					pstmt.setInt(3,word_id);
					pstmt.executeUpdate();
				}else{
					pstmt = (PreparedStatement) conn.prepareStatement("insert into ielts_user(username,word_id,degree) values (?,?,1)");
					pstmt.setString(1,name);
					pstmt.setInt(2,word_id);
					pstmt.executeUpdate();
				}
			}else{
				pstmt = (PreparedStatement) conn.prepareStatement("select * from toefl_user where word_id=?and username=?");
				pstmt.setInt(1,word_id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					degree = rs.getInt("degree");
					degree++;
					pstmt = (PreparedStatement) conn.prepareStatement("update toefl_user set degree=? where username=? and word_id=?");
					pstmt.setInt(1,degree);
					pstmt.setString(2,name);
					pstmt.setInt(3,word_id);
					pstmt.executeUpdate();
				}else{
					pstmt = (PreparedStatement) conn.prepareStatement("insert into toefl_user(username,word_id,degree) values (?,?,1)");
					pstmt.setString(1,name);
					pstmt.setInt(2,word_id);
					pstmt.executeUpdate();
				}
			}
			pstmt.close();
			conn.close();
		}
	}
	
	public void checkHx4() throws ClassNotFoundException, SQLException{
		if(hx4==word){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext extContext =facesContext.getExternalContext();
			HttpSession session =(HttpSession)extContext.getSession(true); 
			   //mes.setMesOperator(session.getAttribute("user").toString());
			String tablename= session.getAttribute("table").toString();
			String name = session.getAttribute("user").toString();
			String url = "jdbc:mysql://localhost:3306/noword?user=root&password=123456";
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url);
			PreparedStatement pstmt;
			if(tablename.equals("cet6")){
				pstmt = (PreparedStatement) conn.prepareStatement("select * from cet6_user where word_id=? and username=?");
				pstmt.setInt(1,word_id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					degree = rs.getInt("degree");
					degree++;
					pstmt = (PreparedStatement) conn.prepareStatement("update cet6_user set degree=? where username=? and word_id=?");
					pstmt.setInt(1,degree);
					pstmt.setString(2,name);
					pstmt.setInt(3,word_id);
					pstmt.executeUpdate();
				}else{
					pstmt = (PreparedStatement) conn.prepareStatement("insert into cet6_user(username,word_id,degree) values (?,?,1)");
					pstmt.setString(1,name);
					pstmt.setInt(2,word_id);
					pstmt.executeUpdate();
				}
			}else if(tablename.equals("ielts")){
				pstmt = (PreparedStatement) conn.prepareStatement("select * from ielts_user where word_id=? and username=?");
				pstmt.setInt(1,word_id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					degree = rs.getInt("degree");
					degree++;
					pstmt = (PreparedStatement) conn.prepareStatement("update ielts_user set degree=? where username=? and word_id=?");
					pstmt.setInt(1,degree);
					pstmt.setString(2,name);
					pstmt.setInt(3,word_id);
					pstmt.executeUpdate();
				}else{
					pstmt = (PreparedStatement) conn.prepareStatement("insert into ielts_user(username,word_id,degree) values (?,?,1)");
					pstmt.setString(1,name);
					pstmt.setInt(2,word_id);
					pstmt.executeUpdate();
				}
			}else{
				pstmt = (PreparedStatement) conn.prepareStatement("select * from toefl_user where word_id=?and username=?");
				pstmt.setInt(1,word_id);
				pstmt.setString(2, name);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()){
					degree = rs.getInt("degree");
					degree++;
					pstmt = (PreparedStatement) conn.prepareStatement("update toefl_user set degree=? where username=? and word_id=?");
					pstmt.setInt(1,degree);
					pstmt.setString(2,name);
					pstmt.setInt(3,word_id);
					pstmt.executeUpdate();
				}else{
					pstmt = (PreparedStatement) conn.prepareStatement("insert into toefl_user(username,word_id,degree) values (?,?,1)");
					pstmt.setString(1,name);
					pstmt.setInt(2,word_id);
					pstmt.executeUpdate();
				}
			}
			pstmt.close();
			conn.close();
		}
	}
}
	