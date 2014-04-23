package com.noword.bean;
import java.io.Serializable;  

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.CartesianChartModel;  
import org.primefaces.model.chart.ChartSeries; 

@ManagedBean
@ViewScoped

  
public class ReviewChart implements Serializable {  
  
    private CartesianChartModel categoryModel;  
  
    public ReviewChart() {  
        createCategoryModel();  
    }  
  
    public CartesianChartModel getCategoryModel() {  
        return categoryModel;  
    }  
  
    private void createCategoryModel() {  
        categoryModel = new CartesianChartModel();  
  
        ChartSeries boys = new ChartSeries();  
        boys.setLabel("Òª¸´Ï°");  
  
        boys.set("5-18", 150);  
        boys.set("5-19", 150);  
        boys.set("5-20", 150);  
        boys.set("5-21", 150);  
        boys.set("5-22", 150);  
  
        ChartSeries girls = new ChartSeries();  
        girls.setLabel("ÒÑ¸´Ï°");  
  
        girls.set("5-18", 52);  
        girls.set("5-19", 60);  
        girls.set("5-20", 110);  
        girls.set("5-21", 150);  
        girls.set("5-22", 120);  
  
        categoryModel.addSeries(boys);  
        categoryModel.addSeries(girls);  
    }  
}