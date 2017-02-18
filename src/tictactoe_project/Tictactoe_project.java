/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author anas
 */

public class Tictactoe_project extends Application {
    
    String text;
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Start Game");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Welcome .... ");
            }
        });
         
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
//                int random = (int)(Math.random() * 3);
//                if (random != 2) {
                    if(i==0)
                        text ="http://icons.iconarchive.com/icons/danieledesantis/playstation-flat/128/playstation-cross-icon.png";
                    else if(i==2)
                        text="http://icons.iconarchive.com/icons/danieledesantis/playstation-flat/128/playstation-circle-icon.png";
                    else
                         text="https://cdn1.iconfinder.com/data/icons/navigation-elements/512/round-empty-circle-function-128.png";
                    pane.add(new ImageView(new Image(text)), j, i);
                   
                    pane.getStyleClass().add("pane");
                }
                    
           
        }
                pane.add(btn, 1, 5);
        
        
        Scene scene = new Scene(pane, 600, 800);
         scene.getStylesheets().add(
      getResource(
        "tictactoe-style.css"
      )
    );
         
         
        
        primaryStage.setTitle("Tictactoe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private String getResource(String resourceName) {
    return getClass().getResource(resourceName).toExternalForm();
  }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
