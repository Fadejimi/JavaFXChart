/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agegrade;

import Util.AgeGroupUtil;
import com.agegrade.model.AgeGroup;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Test
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;
    
    public ObservableList<AgeGroup> class1Data = FXCollections.observableArrayList();
    public ObservableList<AgeGroup> class2Data = FXCollections.observableArrayList();
    public ObservableList<AgeGroup> class3Data = FXCollections.observableArrayList();
    public ObservableList<AgeGroup> class4Data = FXCollections.observableArrayList();
    
    public MainApp()
    {
        // adding data values to class1Data
        class1Data.add(new AgeGroup(AgeGroupUtil.FIRST, 5));
        class1Data.add(new AgeGroup(AgeGroupUtil.SECOND, 7));
        class1Data.add(new AgeGroup(AgeGroupUtil.THIRD, 12));
        class1Data.add(new AgeGroup(AgeGroupUtil.FOURTH, 24));
        class1Data.add(new AgeGroup(AgeGroupUtil.FIFTH, 2));
        
        // adding data values to class2Data
        class2Data.add(new AgeGroup(AgeGroupUtil.FIRST, 25));
        class2Data.add(new AgeGroup(AgeGroupUtil.SECOND, 7));
        class2Data.add(new AgeGroup(AgeGroupUtil.THIRD, 8));
        class2Data.add(new AgeGroup(AgeGroupUtil.FOURTH, 9));
        class2Data.add(new AgeGroup(AgeGroupUtil.FIFTH, 4));
        
        // adding data values to class3Data
        class3Data.add(new AgeGroup(AgeGroupUtil.FIRST, 2));
        class3Data.add(new AgeGroup(AgeGroupUtil.SECOND, 3));
        class3Data.add(new AgeGroup(AgeGroupUtil.THIRD, 90));
        class3Data.add(new AgeGroup(AgeGroupUtil.FOURTH, 23));
        class3Data.add(new AgeGroup(AgeGroupUtil.FIFTH, 0));
        
        // adding data valuse to class4Data
        class4Data.add(new AgeGroup(AgeGroupUtil.FIRST, 3));
        class4Data.add(new AgeGroup(AgeGroupUtil.SECOND, 45));
        class4Data.add(new AgeGroup(AgeGroupUtil.THIRD, 0));
        class4Data.add(new AgeGroup(AgeGroupUtil.FOURTH, 0));
        class4Data.add(new AgeGroup(AgeGroupUtil.FIFTH, 8));   
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Age Group Distribution");
        
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource(
                    "view/mainView.fxml"));
            rootLayout = (AnchorPane) loader.load();
            
            // show Scene containing the rootLayout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
            mainViewController controller = loader.getController();
            controller.setMainApp(this);
            
            primaryStage.show();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public Stage getPrimaryStage()
    {
        return this.primaryStage;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
