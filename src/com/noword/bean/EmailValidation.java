package com.noword.bean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidation implements Validator {

   /* 
   * ������֤��
   */
   @Override
   public void validate(FacesContext ctx, UIComponent component, Object obj)
           throws ValidatorException {
       String email = (String)obj;
       String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
       Pattern pattern = Pattern.compile(check);
       Matcher matcher = pattern.matcher(email);
       if(!matcher.find()){
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"�����ַ������","��������ȷ�������ַ");
           throw new ValidatorException(message);
       }
       
       
   }

}
