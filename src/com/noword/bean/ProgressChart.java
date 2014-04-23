package com.noword.bean;
import java.io.Serializable;  

  
import org.primefaces.model.chart.CartesianChartModel;  
import org.primefaces.model.chart.ChartSeries;  
import org.primefaces.model.chart.LineChartSeries;  
  
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped

public class ProgressChart implements Serializable {  
  
    private CartesianChartModel categoryModel;  
  
    private CartesianChartModel linearModel;  
  
    public ProgressChart() {  
        createCategoryModel();  
        createLinearModel();  
    }  
  
    public CartesianChartModel getCategoryModel() {  
        return categoryModel;  
    }  
  
    public CartesianChartModel getLinearModel() {  
        return linearModel;  
    }  
  
    private void createCategoryModel() {  
        categoryModel = new CartesianChartModel();  
  
        ChartSeries boys = new ChartSeries();  
        boys.setLabel("Boys");  
  
        boys.set("2004", 120);  
        boys.set("2005", 100);  
        boys.set("2006", 44);  
        boys.set("2007", 150);  
        boys.set("2008", 25);  
  
        ChartSeries girls = new ChartSeries();  
        girls.setLabel("Girls");  
  
        girls.set("2004", 52);  
        girls.set("2005", 60);  
        girls.set("2006", 110);  
        girls.set("2007", 135);  
        girls.set("2008", 120);  
  
        categoryModel.addSeries(boys);  
        categoryModel.addSeries(girls);  
    }  
  
    private void createLinearModel() {  
        linearModel = new CartesianChartModel();  
  
        LineChartSeries series1 = new LineChartSeries();  
        series1.setLabel("正在熟悉");  
  
        series1.set(18, 100);  
        series1.set(20, 200);  
        series1.set(22, 300);  
        series1.set(24, 400);  
        series1.set(26, 500);  
  
        LineChartSeries series2 = new LineChartSeries();  
        series2.setLabel("已经掌握");  
        series2.setMarkerStyle("diamond");  
  
        series2.set(18, 150);  
        series2.set(20, 250);  
        series2.set(22, 250);  
        series2.set(24, 350);  
        series2.set(26, 420);  
  
        linearModel.addSeries(series1);  
        linearModel.addSeries(series2);  
    }  
}