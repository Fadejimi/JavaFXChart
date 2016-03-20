/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agegrade;

import Util.AgeGroupUtil;
import com.agegrade.model.AgeGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Test
 */
public class mainViewController {
    
    @FXML
    private PieChart pieChart;
    
    @FXML
    private StackedBarChart<String, Number> stackedBarChart;
    
    @FXML
    private Label meanLabel;
    @FXML
    private Label modeLabel;
    @FXML
    private Label medianLabel;
    
    @FXML
    private ComboBox classBox;
    
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    
    @FXML
    private Label caption;
    
    private XYChart.Series<String, Number> series1 = 
            new XYChart.Series<>();
    private XYChart.Series<String, Number> series2 = 
            new XYChart.Series<>();
    private XYChart.Series<String, Number> series3 = 
            new XYChart.Series<>();
    private XYChart.Series<String, Number> series4 = 
            new XYChart.Series<>();
    
    private MainApp mainApp;
    
    
    @FXML
    private void initialize()
    {
        
    }
    
    public void setMainApp(MainApp mainApp)
    {
        this.mainApp = mainApp;
        
        ObservableList<String> classesData = FXCollections.observableArrayList(
            "Class 1", "Class 2", "Class 3", "Class 4");
        classBox.setItems(classesData);
        
        pieChart.setData(setData(mainApp.class1Data));
        
        classBox.setValue("Class 1");
        setLabels(mainApp.class1Data);
        
        // set the caption
        caption.setText("");
        
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial");
        
        for (final PieChart.Data data : pieChart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, 
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue()) + "%");
                        }
                    });
        }
        
        // set the bar chart
        stackedBarChart.setTitle("Student Population Bar Chart");
        xAxis.setLabel("Age Grade (years)");
        xAxis.setCategories(FXCollections.<String>observableArrayList(
            Arrays.asList(AgeGroupUtil.FIRST, AgeGroupUtil.SECOND, 
                    AgeGroupUtil.THIRD, AgeGroupUtil.FOURTH, 
                    AgeGroupUtil.FIFTH)));
        
        series1.setName("Class 1");
        addData(mainApp.class1Data, series1);
        
        series2.setName("Class 2");
        addData(mainApp.class2Data, series2);
        
        series3.setName("Class 2");
        addData(mainApp.class3Data, series3);
        
        series4.setName("Class 2");
        addData(mainApp.class4Data, series4);
        
        stackedBarChart.getData().addAll(series1, series2, series3, series4);
    }
    
    @FXML
    private void handleClassBoxClicked()
    {
        String classBoxItem = (String) classBox.getSelectionModel().getSelectedItem();
        
        switch(classBoxItem) {
            case("Class 1") :
                pieChart.setData(setData(mainApp.class1Data));
                pieChart.setTitle("Class 1 population chart");
                setLabels(mainApp.class1Data);
                break;
            case("Class 2") :
                pieChart.setData(setData(mainApp.class2Data));
                pieChart.setTitle("Class 2 population chart");
                setLabels(mainApp.class2Data);
                break;
            case("Class 3"):
                pieChart.setData(setData(mainApp.class3Data));
                pieChart.setTitle("Class 3 population chart");
                setLabels(mainApp.class3Data);
                break;
            case("Class 4"):
                pieChart.setData(setData(mainApp.class4Data));
                pieChart.setTitle("Class 4 population chart");
                setLabels(mainApp.class4Data);
                break;
            default:
                break;
        }
    }
    
    @FXML
    private void handleExit()
    {
        System.exit(0);
    }
    
    private void addData(ObservableList<AgeGroup> classData, XYChart.Series
            <String, Number> series)
    {
        for (AgeGroup ageGroup : classData) {
            series.getData().add(new XYChart.Data<String, Number>(ageGroup.group, 
                ageGroup.age));
        }
    }
    
    private ObservableList<PieChart.Data> setData(ObservableList<AgeGroup> data)
    {
        ObservableList<PieChart.Data> dataList = FXCollections.observableArrayList();
        
        for (AgeGroup ageGroup: data) {
            dataList.add(new PieChart.Data(ageGroup.group, ageGroup.age));
        }
        return dataList;
    }
    
    private void setLabels(ObservableList<AgeGroup> dataList)
    {
        List<Integer> data = new ArrayList<>();
        
        for (AgeGroup pieData : dataList)
        {
            data.add((int) pieData.age);
        }
        
        double mean = getMean(data);
        double median = getMedian(data);
        double mode = getMode(data);
        
        meanLabel.setText("" + AgeGroupUtil.round(mean));
        medianLabel.setText("" + AgeGroupUtil.round(median));
        modeLabel.setText("" + AgeGroupUtil.round(mode));
    }
    
    private double getMean(List<Integer> data)
    {
        int l = data.size();
        double total = 0;
        
        for (int i : data) {
            total += i;
        }
        
        return total/l;
    }
    
    private double getMedian(List<Integer> data)
    {
        int l = data.size();
        double mid = 0;
        
        if (l % 2 == 0) {
            int temp = (l/2) - 1;
            for (int i = 0; i < l; i++) {
                if (temp == i || temp + 1 == i) {
                    mid = mid + data.get(i);
                }
            }
            return mid/2;
        }
        else {
            int temp = l/2;
            for (int i = 0; i < l; i++) {
                if (temp == i) {
                    mid = data.get(i);
                }
            }
            return mid;
        }
    }
    
    private double getMode(List<Integer> data)
    {
        int l = data.size();
        int maxValue = 0;
        
        // sort arraylist
        Collections.sort(data);
        
        int maxCount = 0;
        for (int i = 0; i < l; i++) {
            int count = 0;
            for (int j = 0; j < l; j++) {
                if (data.get(i) == data.get(j)) {
                    ++count;
                }
                if (count > maxCount) {
                    maxCount = count;
                    maxValue = data.get(i);
                }
            }
        }
        return maxValue;
    }
}
