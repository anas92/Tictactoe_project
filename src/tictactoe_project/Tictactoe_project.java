/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_project;

import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author anas
 */

public class Tictactoe_project extends Application {
    
    String text;
    public boolean network_mode=false;
    @Override
    public void start(Stage primaryStage) {

        
         Button save= new Button();
        save.setText("Save Game");
        
       
        
        save.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Welcome .... ");
            }
        });
         
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                    if(i==0)
                        text ="http://icons.iconarchive.com/icons/danieledesantis/playstation-flat/128/playstation-cross-icon.png";
                    else if(i==2)
                        text="http://icons.iconarchive.com/icons/danieledesantis/playstation-flat/128/playstation-circle-icon.png";
                    else
                        
                        text="https://cdn1.iconfinder.com/data/icons/navigation-elements/512/round-empty-circle-function-128.png";
                   Button btn3=new Button("");
                   btn3.setMinHeight(128);
                    btn3.setMinWidth(128);
                   btn3.setStyle("-fx-background-image: url('"+text+"')");
                    pane.add(btn3, j, i);
                   
                    pane.getStyleClass().add("pane");
                }
                    
           
        }  
        
                pane.add(save,4,3);
        
                 
                Scene scene = new Scene(pane, 700, 700);
                 scene.getStylesheets().add(
                getResource(
                "tictactoe-style.css"
              )
            );
         TextInputDialog textinput=new TextInputDialog();
         textinput.setTitle("Player Name");
         textinput.setHeaderText("Please, Enter your Name:");
       
         
         Button withpc= new Button();
         withpc.setText("Play with PC");
         withpc.setMinHeight(80);
         withpc.setMinWidth(80);
         withpc.setStyle("-fx-font-size:25px;-fx-color:green;");
         
          withpc.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
//                 textinput.setDialogPane();
                Optional<String> playername = textinput.showAndWait();
                if(playername.isPresent()) {
                    System.out.println("Player name : "+playername.get());
                    primaryStage.setScene(scene);
                }
            }
        });
          
         
        
        Button twoplayers= new Button();
        twoplayers.setText("Two Players");
        twoplayers.setMinHeight(80);
        twoplayers.setMinWidth(80);
        twoplayers.setStyle("-fx-font-size:25px;-fx-color:cyan;-fx-hgap: 10px;-fx-vgap: 10px;");
         
        ToggleGroup group=new ToggleGroup();
        RadioButton same=new RadioButton("Same Machine");
        same.setToggleGroup(group);
        same.setStyle("-fx-hgap: 10px;-fx-vgap: 10px;");
        
        
        RadioButton network=new RadioButton("Network");
        network.setToggleGroup(group);
        
        
        
        TextInputDialog clientinput=new TextInputDialog();
        clientinput.setTitle("Player Name");
         clientinput.setHeaderText("Please, Enter your Name:");
        TextInputDialog same_p1=new TextInputDialog();
        same_p1.setTitle("Player1 Name");
         same_p1.setHeaderText("Please, Enter your Name:");
        TextInputDialog same_p2=new TextInputDialog();
        same_p2.setTitle("Player2 Name");
         same_p2.setHeaderText("Please, Enter your Name:");
        
        
        twoplayers.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(group.getSelectedToggle() == same) {
                    network_mode=false;
                }
                else if(group.getSelectedToggle() == network){
                    network_mode=true;
                }
                
                if(!network_mode){
                      
                     Optional<String> playername = same_p1.showAndWait();
                if(playername.isPresent()) {
                    System.out.println("Player1 name : "+playername.get());
                }
                  Optional<String> playername2 = same_p2.showAndWait();
                if(playername2.isPresent()) {
                    System.out.println("Player2 name : "+playername2.get());
                }
                }else{
                    Optional<String> playername = clientinput.showAndWait();
                if(playername.isPresent()) {
                    System.out.println("Player name : "+playername.get());
                }
                   
                
                }
               
                 primaryStage.setScene(scene);
            }
        });
         
       
        GridPane paneRoot = new GridPane();
        paneRoot.setAlignment(Pos.CENTER);
        
        paneRoot.add(withpc,7,1);
        paneRoot.add(twoplayers,7,3);
        
        paneRoot.add(same,7,4);
        paneRoot.add(network,7,5);
          
          Scene sceneRoot = new Scene(paneRoot, 700, 700); 
          
          
        primaryStage.setTitle("Tictactoe");
        primaryStage.setScene(sceneRoot);
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
