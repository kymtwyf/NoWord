package com.noword.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ToComment {
	public String comment(){
		return "successRegist?faces-redirect=true";
	}
}
