package com.noword.bean;
import java.text.SimpleDateFormat;  
import java.util.Date;  
  
import javax.faces.application.FacesMessage;  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;  
  
import org.primefaces.event.DateSelectEvent;  
@ManagedBean
@ViewScoped
public class CalendarBean {  
  
    private Date date1;  
      
    public Date getDate1() {  
        return date1;  
    }  
  
    public void setDate1(Date date1) {  
        this.date1 = date1;  
    }  
  
    public void handleDateSelect(DateSelectEvent event) {  
        FacesContext facesContext = FacesContext.getCurrentInstance();  
        SimpleDateFormat format = new SimpleDateFormat("d/M/yyyy");  
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getDate())));  
    }  
}  