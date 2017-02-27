/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_project;

import com.sun.javafx.scene.control.skin.LabeledText;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Vector;
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

public class Tictactoe_project extends Application implements Runnable{
    Game game;
    Player p1=new Player();
    Player p2=new Player();
    Socket s;
    DataInputStream dis;
    PrintStream ps;
    int movesPlayer1[][]= p1.movesPlayer;
    int movesPlayer2[][]= p2.movesPlayer;
//    char BoardGame[][];
    int countVS=0;
    String text;
    static int mode;
    public int player=1;
    public boolean network_mode=false;
    public int counter = 0;
    public int indx_x;
    public int indx_y;
    ArrayList <Button>btns;
    Stage test;
    String Pwinner;
    Thread th;
    Boolean switchFlag=true;
  
    @Override
    public void start(Stage primaryStage) {
        
        test=primaryStage;
        primaryStage.setTitle("Tictactoe");
        primaryStage.setScene(startScene(primaryStage));
        primaryStage.show();
    }
    private String getResource(String resourceName) {
    return getClass().getResource(resourceName).toExternalForm();
  }
    @Override
    public void run()
	{
//	
            	try{		
			while(true){	
                            System.out.println("run");
                        String result= dis.readLine();  
                       // switchFlag=true;
                        char[] tempCahr =result.toCharArray();
                        if(tempCahr[0]=='@')
                        {
                             Pwinner=result.substring(1);
                           
                            showWinner(Pwinner);
//                              th.stop();
                            
                        }
                        else if(tempCahr.length==9){
                        

                            for (int i = 0; i < tempCahr.length; i++) {
//                                if(tempCahr[i]!='c')
//                                {
//                                 if(tempCahr[i]==p1.shape)
//                                    p1.countShape++;
//                                else
//                                    countVS++;   
//                                }
                               
                                game.BoardChar[i]=tempCahr[i];
                            }
                        
//                        if(p1.countShape<=countVS)
//                                   switchFlag=true;
                        
                        game.Board[0][0]=tempCahr[0];
                        game.Board[0][1]=tempCahr[1];
                        game.Board[0][2]=tempCahr[2];
                        game.Board[1][0]=tempCahr[3];
                        game.Board[1][1]=tempCahr[4];
                        game.Board[1][2]=tempCahr[5];
                        game.Board[2][0]=tempCahr[6];
                        game.Board[2][1]=tempCahr[7];
                        game.Board[2][2]=tempCahr[8];
                            DisplayCharArr(game.Board);

//                       if(switchFlag==true)
//                       {
                            for (int i = 0 ; i < 9; i++) {

                                if(game.BoardChar[i]=='x')
                                {
                                    text="x.png";
                                    btns.get(i).setStyle("-fx-background-image: url('"+text+"')");
                                }
                                else if(game.BoardChar[i]=='o')
                                {   text="o.png";
                                 btns.get(i).setStyle("-fx-background-image: url('"+text+"')");
                                btns.get(i).setDisable(true);
                                }
                                
                            }
//                       }
                          if(Game.moves(game.Board)==p1.shape)
                          {
                            p1.is_win=1;
                            //showWinner(Pwinner);
//                          
                          }
               }
			}
                        }catch(Exception ex)
			{
				ex.printStackTrace();
			}
                
	}
    // to reset players objects and counter when play again
    void reset(){
         p1 = new Player();
         p2 = new Player();
         movesPlayer1= p1.movesPlayer;
         movesPlayer2= p2.movesPlayer;
         game=new Game();
         btns=new ArrayList<>();
         counter = 0;
    }
    // to display board of every player as integer
    void Display(int [][]arr){
    for(int[] pi : arr){
                String str = "";    
                for(int i = 0; i < pi.length; i++){
                    str= str + Integer.toString(pi[i]) + " ";
                }//for
                System.out.println(str);
                }
    }
    // to display board game of players as string
     String DisplayCharArr(char [][]arr){
         String str = ""; 
    for(char[] pi : arr){
                for(int i = 0; i < pi.length; i++){
                    str+=Character.toString(pi[i]);
                }
                }
    return str;
    }
     void showWinner(String winner)
     {
          Platform.runLater(()->{
          Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Win");
                    alert.setHeaderText(winner+" is Winner");
                    alert.setContentText("Do you want play Avideo as gift?");
                   
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                       // reset();
//                        game=new Game();
//                        game.mode=2;
                      //  mainScene(test);
                    }else{
                        Platform.exit();
                    }
                    });
                    
     }
     // start scene when starting agame
    Scene startScene(Stage primaryStage){
        
        //test=primaryStage;
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
        // dialogs of network
        TextInputDialog clientinput=new TextInputDialog();
        clientinput.setTitle("Player Name");
        clientinput.setHeaderText("Please, Enter your Name:");
        TextInputDialog serverinput=new TextInputDialog();
        serverinput.setTitle("Server IP");
        serverinput.setHeaderText("Please, Enter IP of Server:");
        TextInputDialog shapeClient=new TextInputDialog();
        shapeClient.setTitle("Shape Type:");
        shapeClient.setHeaderText("Choose x or o to start");
        // dialog of locally machine
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
            
            // tow players locally
            if(!network_mode){
                game = new Game();
                game.mode = 1;
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
                // network mode
                game = new Game();
                game.mode = 2;
                Optional<String> playername = clientinput.showAndWait();
                if(playername.isPresent()) {
                    // run server to listen to clients 
                  // ServerSide server= new ServerSide();
                   
                   game.mode=2;
                    p1.playerId=1;
                    p1.mode=1;
                    p1.name=playername.get();
                    System.out.println("Player name : "+playername.get());
                }
                Optional<String> serverip =serverinput.showAndWait();
                if(serverip.isPresent()) {
                    p1.serverIp=serverip.get();
                    System.out.println(p1.serverIp);
                }
                 Optional<String> shapeType =shapeClient.showAndWait();
                if(shapeType.isPresent()) {
                    char[]chArr=shapeType.get().toCharArray();
                    p1.shape=chArr[0];
                    System.out.println(p1.shape);
                }
                // open socket and connect it to server
                th=new Thread(this);
                try{
			s=new Socket(p1.serverIp,5005);
			dis=new DataInputStream(s.getInputStream());
			ps=new PrintStream(s.getOutputStream());
                        th.start();
		}catch(IOException ex)
				{
					ex.printStackTrace();
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
    // main scene when choose between game modes
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
            btns.get(m).setOnAction((ActionEvent e) -> 
            {
                Button b=(Button)e.getSource();
                b.getStyleClass().add("b");
                indx_x=GridPane.getRowIndex(b);
                indx_y=GridPane.getColumnIndex(b);
                 // Network board game is sent to server
              if(game.mode  == 2 && p1.is_win != 1){
                         
                         if(p1.shape=='x'){
                             
                        game.Board[indx_x][indx_y]='x';
                        text="x.png";
                         }
                        else if(p1.shape=='o'){
                        game.Board[indx_x][indx_y]='o';
                        text="o.png";
                        }
                         b.setStyle("-fx-background-image: url('"+text+"');");
                         b.setDisable(true);
                        if(Game.moves(game.Board)==p1.shape)
                            p1.is_win=1;
                        ps.println(DisplayCharArr(game.Board));
                        //switchFlag=false;
                    }
              else if(p1.is_win != 1){
                if(counter%2==0){   // even
                    System.out.println("even");
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
                    game.Board[indx_x][indx_y]='o';
                    movesPlayer2[indx_x][indx_y]=1;
                    p2.is_win=p2.moves(movesPlayer2);
                    Display(movesPlayer2);
                    text="o.png";
                    b.setStyle("-fx-background-image: url('"+text+"');");
                    b.setDisable(true);
                    counter++;
                }else if(game.mode == 0) {
                    System.out.println("Computer's turn...");
                }
              }
            
                
                System.out.println("game mode: "+game.mode);
                System.out.println("\n");
                System.out.println(p1.is_win+" "+p2.is_win);
                
                if(p1.is_win==1){ 
                    if(game.mode==2)
                           ps.println("@"+p1.name);
                    else
                    showWinner(p1.name);
                }else if(p2.is_win==1){
                     DisplayCharArr(game.Board);
                     showWinner(p2.name);
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
    char BoardChar[]={'c','c','c','c','c','c','c','c','c'};
    char Board[][]={{'c','c','c'},{'c','c','c'},{'c','c','c'}};

    Game() {
        
    }
    
    static char moves(char[][] positions){
        System.out.println("ok");
        int flagRowX=0;
        int flagRowO=0;
        int flagColX=0;
        int flagColO=0;
     
        // row 
        for (int i = 0; i < 3; i++) 
        {
            flagRowX=0;
            flagRowO=0;
            for (int j = 0; j < 3; j++) {
                if(positions[i][j]=='x' )
                    flagRowX++;
                else if(positions[i][j]=='o' )
                    flagRowO++;

            }
            
                if(flagRowX==3)
                 return 'x';
                if(flagRowO==3)
                 return 'o'; 
   
        }
        
         // column
        for (int i = 0; i < 3; i++) 
        {
            flagColX=0;
            flagColO=0;
            for (int j = 0; j < 3; j++) {
                if(positions[j][i]=='x' )
                    flagColX++;
                else if(positions[j][i]=='o' )
                    flagColO++;
            }
            
                if(flagColX==3)
                 return 'x';
                if(flagColO==3)
                 return 'o';          
        }
        
          if(((positions[0][0]=='x' && positions[1][1]=='x')&&positions[2][2]=='x')||((positions[0][2]=='x' && positions[1][1]=='x')&&positions[2][0]=='x'))
                return 'x';
          if(((positions[0][0]=='0' && positions[1][1]=='o')&&positions[2][2]=='0')||((positions[0][2]=='0' && positions[1][1]=='0')&&positions[2][0]=='0'))
                return '0';
        
          
          
            return 'c';  
    }
     
    
}
class Player{
    
    int playerId;
    String name;
    String serverIp;
    int countShape=0;
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
