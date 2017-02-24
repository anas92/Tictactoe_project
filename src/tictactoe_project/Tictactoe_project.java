/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_project;

import com.sun.javafx.scene.control.skin.LabeledText;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.IntStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author anas
 */

public class Tictactoe_project extends Application {
    Game game;
    Player p1=new Player();
    Player p2=new Player();
    int movesPlayer1[][]= p1.movesPlayer;
    int movesPlayer2[][]= p2.movesPlayer;
    String text;
    static int mode;
    String s;
    public int player=1;
    public boolean network_mode=false;
    public int counter = 0;
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
              game =  new Game();
              game.mode = 0;
              Optional<String> playername = textinput.showAndWait();
              if(playername.isPresent()) {
                    p1.shape='x';
                    p1.playerId=1;
                    p1.mode=0;
                    p1.name=playername.get();
                  System.out.println("Player name : "+playername.get());
                  
                   p2.shape='o';
                   p2.playerId=0;
                   p2.mode=0;
                   p2.name="Computer";
                   System.out.println("Player2 name : "+p2.name);
                    
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
            game = new Game();
            game.mode = 1;
            if(group.getSelectedToggle() == same) {
                network_mode=false;
            }
            else if(group.getSelectedToggle() == network){
                network_mode=true;
            }
            
            if(!network_mode){
                Optional<String> playername = same_p1.showAndWait();
                if(playername.isPresent()) {
                    p1.shape='x';
                    p1.playerId=1;
                    p1.mode=1;
                    p1.name=playername.get();
                    System.out.println("Player1 name : "+p1.name);
                }
                Optional<String> playername2 = same_p2.showAndWait();
                if(playername2.isPresent()) {
                    p2.shape='o';
                    p2.playerId=2;
                    p2.mode=1;
                    p2.name=playername2.get();
                    System.out.println("Player2 name : "+p2.name);
                }
            }else{
                Optional<String> playername = clientinput.showAndWait();
                if(playername.isPresent()) {
                    p1.shape='x';
                    p1.playerId=1;
                    p1.mode=2;
                    p1.name=playername.get();
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
    
    @SuppressWarnings("empty-statement")
     Scene mainScene(Stage primaryStage) {
        BorderPane bpane=new BorderPane();
        
        MenuBar bar=MyScenes.myMenuBar("Game",  new String[]{"Local","Machine","Network"});
        bpane.setTop(bar);
        Button save= new Button();
        save.setText("Save Game");
        save.setStyle("-fx-background-color: yellow;");
//        save.setStyle("-fx-color: green;");
  
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
            btns.get(m).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    Button b=(Button)e.getSource();
                    b.getStyleClass().add("b");
                    indx_x=GridPane.getRowIndex(b);
                    indx_y=GridPane.getColumnIndex(b);
                    //System.out.println("clicked");
                    
                    if(counter%2==0){   // even
                        System.out.println("even");
                        s="X";
                        text="x.png";
                        player=1;
                        movesPlayer1[indx_x][indx_y]=1;
                        p1.is_win=p1.moves(movesPlayer1);
                        Display(movesPlayer1);
                        text ="x.png";
                        b.setStyle("-fx-background-image: url('"+text+"');");
                        b.setDisable(true);
                        counter++;
                        //computer's turn , player1 hasn'y won and game not over yet
                        if(game.mode  == 0 && p1.is_win != 1 && counter < 9) {
                            int randomNum = ThreadLocalRandom.current().nextInt(0, 8+1);
                           
                            System.out.println("Computer's turn...");
                            System.out.println("Random move   : "+randomNum);
                             
                            Node target = btns.get(randomNum);
                            
                            while(target.isDisabled()) {
                                System.out.println("Disabled");
                                randomNum = ThreadLocalRandom.current().nextInt(0, 8+1);
                                target = btns.get(randomNum);
                                System.out.println("Random move   : "+randomNum);
                            }
                            player=2;
                            movesPlayer2[randomNum/3][randomNum%3]=1;
                            
                            s="O";
                            p2.is_win=p2.moves(movesPlayer2);
                            Display(movesPlayer2);
                            text="o.png";
                            target.setDisable(true);    
                            target.setStyle("-fx-opacity: 1.0 ;");
                            target.setStyle("-fx-background-image: url('"+text+"');");
                            counter++;                            
                        }
                    }else if(game.mode == 1) {      // odd 2players locally
                        System.out.println("odd");
                        player=2;
                        movesPlayer2[indx_x][indx_y]=1;
                        s="O";
                        p2.is_win=p2.moves(movesPlayer2);
                        Display(movesPlayer2);
                        text="o.png";
                        b.setStyle("-fx-background-image: url('"+text+"');");
                        b.setDisable(true);
                        counter++;
                    }else if(game.mode == 0) {
                        System.out.println("Computer's turn...");
                    }
                    
//                System.out.println(counter);//
                  System.out.println("game mode: "+game.mode);
                  System.out.println("\n");
//                System.out.println(indx_x+" "+indx_y);
                  System.out.println(p1.is_win+" "+p2.is_win);

//                b.setText(s);
//                b.setCancelButton(true);
if(p1.is_win==1){
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Win");
    alert.setHeaderText(p1.name+" is Winner");
    alert.setContentText("Do you want play again ?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        p1 = new Player();
        p2 = new Player();
        movesPlayer1= p1.movesPlayer;
        movesPlayer2= p2.movesPlayer;
        counter = 0;
        start(primaryStage);
    }else{
        Platform.exit();
    }
}else if(p2.is_win==1){
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Win");
    alert.setHeaderText(p2.name+" is Winner");
    alert.setContentText("Do you want play again ?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        p1 = new Player();
        p2 = new Player();
        movesPlayer1= p1.movesPlayer;
        movesPlayer2= p2.movesPlayer;
        counter = 0;
        start(primaryStage);
    }else{
        Platform.exit();
    }
}
else if(counter == 9) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Tie");
    alert.setHeaderText("It's a tie!");
    alert.setContentText("Do you want play again ?");
    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        p1 = new Player();
        p2 = new Player();
        movesPlayer1= p1.movesPlayer;
        movesPlayer2= p2.movesPlayer;
        counter = 0;
        start(primaryStage);
    }else{
        Platform.exit();
    }
}
                }
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
    public int mode;
    String[] Board = new String[9];

    Game() {
        
    }
    
}
class Player{
    
    int playerId;
    String name;
    char shape;
    int is_win;
    int mode;
    int movesPlayer[][]={{0,0,0},{0,0,0},{0,0,0}};

    
    Player() {
 
    }
    
    int moves(int[][] positions){
        System.out.println("ok");
        int flag=1;
        int flag2=1;
     
        // row
        for (int i = 0; i < 3; i++) {
            flag=1;
            for (int j = 0; j < 3; j++) {
                if(positions[i][j]==0 )
                flag=0;
            }
            
                if(flag==1)
                 return 1;
            
        }
        // column
        for (int i = 0; i < 3; i++) {
             flag2=1;
            for (int j = 0; j < 3; j++) {
                if(positions[j][i]==0 )
                flag2=0;
            }
            if(flag2==1)
                return 1;
        }
         if(((positions[0][0]==1 && positions[1][1]==1)&&positions[2][2]==1)||((positions[0][2]==1 && positions[1][1]==1)&&positions[2][0]==1))
                return 1;
        
            return 0;
    }
}
