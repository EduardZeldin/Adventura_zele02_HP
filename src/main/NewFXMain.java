/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logika.*;
import uiText.TextoveRozhrani;

/**
 *
 * @author User
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button tlacitko = new Button();
        tlacitko.setText("Spust adventuru'");
        tlacitko.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                IHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            }
        });
        
        TextArea textArea = new TextArea();
        
        StackPane root = new StackPane();
        root.getChildren().add(tlacitko);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
