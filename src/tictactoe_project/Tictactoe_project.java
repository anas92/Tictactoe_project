/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_project;

import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author anas
 */

public class Tictactoe_project extends Application {
    
    Player p1=new Player();
    Player p2=new Player();
    int movesPlayer1[][]={{0,0,0},{0,0,0},{0,0,0}};
    int movesPlayer2[][]={{0,0,0},{0,0,0},{0,0,0}};
    String text;
    static int mode;
    String s;
    public int player=1;
    public boolean network_mode=false;
    public int counter;
    public int indx_x;
    public int indx_y;
    ArrayList <Button>btns;
    @Override
    public void start(Stage primaryStage) {
        
        primaryStage.setTitle("Tictactoe");
        primaryStage.setScene(startScene(primaryStage));
        primaryStage.show();
    }
  
    
    private String getResource(String resourceName) {
    return getClass().getResource(resourceName).toExternalForm();
  }
    Scene startScene(Stage primaryStage){
        
        
        TextInputDialog textinput=new TextInputDialog();
         textinput.setTitle("Player Name");
         textinput.setHeaderText("Please, Enter your Name:");
       
         
         Button withpc= new Button();
         withpc.setText("Play with PC");
         withpc.setMinHeight(80);
         withpc.setMinWidth(80);
         withpc.setStyle("-fx-font-size:25px;-fx-color:green;");
         
          withpc.setOnAction((ActionEvent event) -> {
              //                 textinput.setDialogPane();
              Optional<String> playername = textinput.showAndWait();
              if(playername.isPresent()) {
                  System.out.println("Player name : "+playername.get());
                  primaryStage.setScene(mainScene(primaryStage));
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
        
        
        twoplayers.setOnAction((ActionEvent event) -> {
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
            
            primaryStage.setScene(mainScene(primaryStage));
         });
         GridPane paneRoot = new GridPane();
        BorderPane bpaneRoot=new BorderPane();
        MenuBar bar2=MyScenes.myMenuBar("Game",  new String[]{"Local","Machine","Network"});
         
        bpaneRoot.setTop(bar2);
        paneRoot.getStyleClass().add("paneRoot");
        paneRoot.setAlignment(Pos.CENTER);
        
        paneRoot.add(withpc,5,1);
        paneRoot.add(twoplayers,5,3);
        
        paneRoot.add(same,5,5);
        paneRoot.add(network,6,5);
        paneRoot.setHgap(10);
        paneRoot.setVgap(40);
        bpaneRoot.setCenter(paneRoot);
          
          Scene sceneRoot = new Scene(bpaneRoot, 600, 600); 
          return sceneRoot;
    }
    void Display(int [][]arr){
    for(int[] pi : arr){
                 String str = "";    
                for(int i = 0; i < pi.length; i++){
                    str= str + Integer.toString(pi[i]) + " ";
                }//for
                System.out.println(str);
                }
    }
    
     Scene mainScene(Stage primaryStage)
         {
             
        
        
         BorderPane bpane=new BorderPane();
        
        MenuBar bar=MyScenes.myMenuBar("Game",  new String[]{"Local","Machine","Network"});
        bpane.setTop(bar);
         Button save= new Button();
        save.setText("Save Game");
        
       
        
        save.setOnAction((ActionEvent event) -> {
            System.out.println("Welcome .... ");
         });
          btns=new ArrayList<>();
        GridPane pane = new GridPane();
    
        
        pane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                  Button btn3=new Button(""); 
                   btn3.setMinHeight(128);
                    btn3.setMinWidth(128);
                   
                   btns.add(btn3);
                    pane.add(btn3, j, i);
                    pane.getStyleClass().add("pane");
                }
                    
           
        }
        for (int m = 0; m < btns.size(); m++) {
            text="eo.png";
            btns.get(m).setStyle("-fx-background-image: url('"+text+"')");
            btns.get(m).setOnAction((ActionEvent e)->
            {
                 Button b=(Button)e.getSource();
                indx_x=GridPane.getRowIndex(b);
                indx_y=GridPane.getColumnIndex(b);
                System.out.println("clicked");
                                        // even
                if(counter%2==0){
                     System.out.println("even");
                     
                    s="X";
                    text="x.png";
                    player=1;
                    movesPlayer1[indx_x][indx_y]=1;
                     Display(movesPlayer1);
                    text ="x.png";
                }else{                  // odd
                     System.out.println("odd");
                    player=2;
                    movesPlayer2[indx_x][indx_y]=2;
                    s="O";
                     Display(movesPlayer2);
                    text="o.png";
                }
               
//                System.out.println(counter);
                b.setStyle("-fx-background-image: url('"+text+"')");
                
               
//                System.out.println(indx_x+" "+indx_y);
                System.out.println("\n");
               
                
//                b.setText(s);
                b.setDisable(true);
                counter++;
                    });  
		}
               
                pane.add(save,2,3);
                bpane.setCenter(pane);
                 Scene scene = new Scene(bpane, 600, 600);
                 scene.getStylesheets().add(
                getResource(
                "tictactoe-style.css"
              )
            );
                 primaryStage.setScene(startScene(primaryStage));
                 return scene;
                 
             }   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
class MyScenes {
    
    static MenuBar myMenuBar(String menuName,String[] menuItemsNames)
    {
        
        MenuBar Mbar = new MenuBar();
        Menu menu=new Menu(menuName);
        for (String menuItemsName : menuItemsNames) {
            MenuItem mItem = new MenuItem(menuItemsName);
            if(menuItemsName=="Local"){
            mItem.setOnAction((ActionEvent e)->{
            System.out.println("Local");
            Tictactoe_project.mode=0;
        });
            }else if(menuItemsName=="Machine"){
                mItem.setOnAction((ActionEvent e)->{
            System.out.println("Machine");
            Tictactoe_project.mode=1;
        });
            }else if(menuItemsName=="Network"){
                mItem.setOnAction((ActionEvent e)->{
                System.out.println("Network");
                Tictactoe_project.mode=2;
            
            });
            }
            menu.getItems().add(mItem);
        }
              
         Mbar.getMenus().add(menu);
         return Mbar;
         
        
    }
}
class Game{
    
    int gameId;
    int winner_id;
    String date;
    
    
}
class Player{
    
    int playerId;
    String name;
    char shape;
    int is_win;
    int mode;
    
    void moves(int[][] positions){
        
    }
}
