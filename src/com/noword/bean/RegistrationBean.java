package com.noword.bean;


import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.noword.bean.SendMail;

@ManagedBean
@SessionScoped
public class RegistrationBean {
  private String userName, code,reCode, email,vercode,auth_code="",temp_auth_code,check_username;
  public DateBase dateBase = new DateBase();
  public SendMail sendMail = new SendMail();
  public String getUserName() {
    return(userName);
  }
  
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  public String getCode() {
    return(code);
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  public String getReCode() {
	    return(reCode);
	  }
	  
  public void setReCode(String reCode) {
	    this.reCode = reCode;
	  }
  
  public String getVercode() {
    return(vercode);
  }
  
  public void setVercode(String vercode) {
    this.vercode = vercode;
  }
  
  public String getAuth_code() {
	    return(auth_code);
	  }
	  
  public void setAuth_code(String auth_code) {
	    this.auth_code = auth_code;
	  }
	  
  public String getCheck_username() {
	    return(check_username);
	  }
	  
public void setCheck_username(String check_username) {
	    this.check_username = check_username;
	  }
  public String getEmail() {
		    return(email);
		  }
		  
  public void setEmail(String email) {
		    this.email = email;
		  }
  public String getTemp_auth_code() {
			    FacesContext facesContext = FacesContext.getCurrentInstance();
			    ExternalContext extContext =facesContext.getExternalContext();
			    HttpSession session =(HttpSession)extContext.getSession(true); 
			    temp_auth_code = session.getAttribute("key").toString();
			    return(temp_auth_code);
			  }
			  
  public void setTemp_auth_code(String temp_auth_code) {
			    this.temp_auth_code = temp_auth_code;
			  }
  public String doRegistration() throws SQLException, ClassNotFoundException {
	    getTemp_auth_code();
	    /*if(!temp_auth_code.equalsIgnoreCase(vercode)||!(dateBase.insert(userName,code,email)))
	    {
	    	if(!temp_auth_code.equalsIgnoreCase(vercode))
		    {
		    	auth_code = "验证码错误";
		    }
	    	if(!(dateBase.insert(userName,code,email))){
		    	check_username="该用户名已经被注册";
		  	}
	    	return ("regist?faces-redirect=true");
	    }
	    else
	    {
	    	sendMail.sendEmail(userName,code,email);  
	  		return("successRegist?faces-redirect=true");
	    }*/
	    /*if(!temp_auth_code.equalsIgnoreCase(vercode))
	    {
	    	auth_code = "验证码错误";
	    	return ("regist?faces-redirect=true");
	    }
	    else*/ 
	    if(!(dateBase.insert(userName,code,email))){
	    	check_username ="该用户名已经被注册";
	  		return ("regist?faces-redirect=true");
	  	}else if(!temp_auth_code.equalsIgnoreCase(vercode))
	    {
	    	auth_code = "验证码错误";
	    	return ("regist?faces-redirect=true");
	    }
	  	else{
	  		sendMail.sendEmail(userName,code,email);  
	  		return("successRegist?faces-redirect=true");
	  	}
  }
}
