/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe_project;

import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.paint.Color;

/**
 *
 * @author anas
 */

public class Tictactoe_project extends Application implements Runnable{
    // AI Game Variables
    BoardAI boardAI;
    Random rand;

    Game game;
    Player p1=new Player();
    Player p2=new Player();
    Socket s;
    DataInputStream dis;
    PrintStream ps;
    int movesPlayer1[][]= p1.movesPlayer;
    int movesPlayer2[][]= p2.movesPlayer;
    int countVS=0;
    String text;
    static int mode;
    public int player=1;
    public boolean network_mode=false;
    public int counter = 0;
    public int indx_x;
    public int indx_y;
    public static ArrayList <Button>btns;
    Stage test;
    String Pwinner;
    Thread th;
    Boolean switchFlag=true;
    Boolean switchFlag2=true;
    MediaPlayer mediaPlayer;
    Label label1;
    Image image;
    ImageView imgview;
    dbConnection myDB;
    int maxGameId;
    @Override
    public void start(Stage primaryStage) {

        test=primaryStage;
        primaryStage.setTitle("Tictactoe");
        primaryStage.setScene(firstScene(primaryStage));
        primaryStage.show();
        String path = "lifeforrent.mp3";
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
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
                        else if(tempCahr[0]=='1'){
                            switchFlag=false;
                            switchFlag2=true;
                        }
                        else if(tempCahr[0]=='2'){
                            switchFlag=true;
                            switchFlag2=false;
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
                                    btns.get(i).setDisable(true);
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
           mediaPlayer.stop();
          Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Win");
                    alert.setHeaderText(winner+" is Winner");
                    // play video to winner only
                    if((p1.is_win==1 && game.mode==2)||game.mode==1)
                        alert.setContentText("Do you want play A video as gift?");
  
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                                if((p1.is_win==1 && game.mode==2)||game.mode==1){ 
                               
                                    StackPane root = new StackPane();

                                   MediaPlayer player = new MediaPlayer( new Media(getClass().getResource("nw.mp4").toExternalForm()));
                                   MediaView mediaView = new MediaView(player);

                                   root.getChildren().add( mediaView);
                                   Scene scene = new Scene(root,600,600);
                                   Stage primaryStage=new Stage();
                                   primaryStage.setScene(scene);
                                   primaryStage.show();

                                        player.play();
                                   }
                                else
                                    Platform.exit();
                                   

                    }else{
                        Platform.exit();
                    }
                    });

     }


     // start scene when starting agame
    Scene firstScene(Stage stage){

        //test=stage;
        myDB=new dbConnection();
        TextInputDialog textinput=new TextInputDialog();
         textinput.setTitle("Player Name");
         textinput.setHeaderText("Please, Enter your Name:");


         Button withpc= new Button();
         withpc.setText("Play with PC");
         withpc.setMinHeight(80);
         withpc.setMinWidth(80);
         withpc.setStyle("-fx-font-size:25px;-fx-color:lightgreen;-fx-background-radius:20");


        ToggleGroup groupAI =new ToggleGroup();
        RadioButton beginner=new RadioButton("Beginner");
        beginner.setToggleGroup(groupAI);
        beginner.setStyle("-fx-hgap: 10px;-fx-vgap: 10px;");

        RadioButton novice=new RadioButton("Novice");
        novice.setToggleGroup(groupAI);
        novice.setStyle("-fx-hgap: 10px;-fx-vgap: 10px;");

        RadioButton hard=new RadioButton("Hard");
        hard.setStyle("-fx-hgap: 10px;-fx-vgap: 20px;");
        hard.setToggleGroup(groupAI);

          withpc.setOnAction((ActionEvent event) -> {
              game =  new Game();
              game.mode = 0;
              myDB.insertGame(game.mode);
              maxGameId=myDB.getMaxGameId();
              boardAI = new BoardAI();
              rand = new Random();

              boardAI.displayBoard();

              if(groupAI.getSelectedToggle() == beginner) {
                    boardAI.levelOfIntelligence = 0;
              }
              else if(groupAI.getSelectedToggle() == novice){
                    boardAI.levelOfIntelligence = 1;
              }

              Optional<String> playername = textinput.showAndWait();
              if(playername.isPresent()) {
                  p1.shape='x';
                  p1.playerId=0;
                  p1.mode=0;
                  p1.name="Computer";
                  System.out.println("Player1 name : "+p1.name);
                  myDB.insertPlayer(p1.name, p1.mode, p1.shape);
                  p1.playerId= myDB.getMaxPlayerId();
                  myDB.insertGamePlayer(maxGameId, p1.playerId);

                  p2.shape='o';
                  p2.playerId=1;
                  p2.mode=0;
                  p2.name=playername.get();
                  System.out.println("Player2 name : "+p2.name);
                  myDB.insertPlayer(p2.name, p2.mode, p2.shape);
                  p2.playerId= myDB.getMaxPlayerId();
                  myDB.insertGamePlayer(maxGameId,p2.playerId);

                  stage.setScene(mainScene(stage));
              }

                //System.out.println("Who's gonna move first? (1)Computer (2)User: ");
                //int choice = b.scan.nextInt();
                int choice = 2; //User first ..
                if(choice == 1) {
                    Point p = new Point(rand.nextInt(3), rand.nextInt(3));
                    boardAI.placeAMove(p, 1);
                    boardAI.displayBoard();
                }

        });

        Button twoplayers= new Button();
        twoplayers.setText("Two Players");
        twoplayers.setMinHeight(80);
        twoplayers.setMinWidth(80);
        twoplayers.setStyle("-fx-font-size:25px;-fx-color:cyan;-fx-hgap: 10px;-fx-vgap: 10px;-fx-background-radius:20");

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
            game = new Game();
            game.mode = 1;
            myDB.insertGame(game.mode);
            maxGameId=myDB.getMaxGameId();
            if(group.getSelectedToggle() == network) {
                network_mode=true;
            }
            else /*if(group.getSelectedToggle() == same)*/{
                network_mode=false;
            }

            // two players locally
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
                    myDB.insertPlayer(p1.name, p1.mode, p1.shape);
                    p1.playerId= myDB.getMaxPlayerId();
                    myDB.insertGamePlayer(maxGameId, p1.playerId);
                }
                Optional<String> playername2 = same_p2.showAndWait();
                if(playername2.isPresent()) {
                    p2.shape='o';
                    p2.playerId=2;
                    p2.mode=1;
                    p2.name=playername2.get();
                    System.out.println("Player2 name : "+p2.name);
                    myDB.insertPlayer(p2.name, p2.mode, p2.shape);
                    p2.playerId= myDB.getMaxPlayerId();
                    myDB.insertGamePlayer(maxGameId, p2.playerId);
                }

            }else{
                // network mode
                game = new Game();
                game.mode = 2;
                Optional<String> playername = clientinput.showAndWait();
                if(playername.isPresent()) {
                // run server to listen to clients
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
                    myDB.insertPlayer(p1.name, p1.mode, p1.shape);
                    p1.playerId= myDB.getMaxPlayerId();
                    myDB.insertGamePlayer(maxGameId, p1.playerId);
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

            stage.setScene(mainScene(stage));
         });
        GridPane paneRoot = new GridPane();
        BorderPane bpaneRoot=new BorderPane();
        MenuBar bar2=MyScenes.myMenuBar("Game",  new String[]{"Local","Machine","Network"});

        bpaneRoot.setTop(bar2);
        paneRoot.getStyleClass().add("paneRoot");
        paneRoot.setAlignment(Pos.CENTER);

        paneRoot.add(withpc,4,0);
        paneRoot.add(beginner,4,1);
        paneRoot.add(novice,4,2);
        paneRoot.add(hard,4,3);

        paneRoot.add(twoplayers,4,6);
        paneRoot.add(same,4,7);
        paneRoot.add(network,4,8);
        paneRoot.setHgap(10);
        paneRoot.setVgap(10);
        paneRoot.setStyle("-fx-background-color: linear-gradient(green 0%, cyan 100%);");

        bpaneRoot.setCenter(paneRoot);

        Scene sceneRoot = new Scene(bpaneRoot, 400, 550);

        return sceneRoot;
    }
    // main scene when choose between game modes
     Scene mainScene(Stage stage) {
        BorderPane bpane=new BorderPane();

        MenuBar bar=MyScenes.myMenuBar("Game",  new String[]{"Local","Machine","Network"});
        bpane.setTop(bar);
        Button save= new Button();
        save.setText("Save Game");
        save.setStyle("-fx-background-color: yellow;");
//        save.setStyle("-fx-color: green;");

        save.setOnAction((ActionEvent event) -> {
          System.out.println("Welcome .... ");
          TextInputDialog textinput=new TextInputDialog();
          textinput.setTitle("Save Game");
          textinput.setHeaderText("Please, Enter name for this game to save...:");
                      Optional<String> result = textinput.showAndWait();
                      if(result.isPresent()) {

                          myDB.updateGame(result.get(),maxGameId);
                      }
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

        label1 = new Label("'s Turn");
        image = new Image("/x.png", true);
        imgview = new ImageView(image);
        imgview.setStyle("-fx-padding: 10 10 10 10");
        imgview.setFitHeight(20);
        imgview.setFitWidth(20);
        label1.setGraphic(imgview);
        label1.setTextFill(Color.web("#0076a3"));
        bpane.setBottom(label1);

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

                        if(p1.shape=='x'&& switchFlag){

                        game.Board[indx_x][indx_y]='x';
                        text="x.png";
                        ps.println("1");
                         b.setStyle("-fx-background-image: url('"+text+"');");
                         b.setDisable(true);
                        }
                        else if(p1.shape=='o'&& switchFlag2){
                        game.Board[indx_x][indx_y]='o';
                        text="o.png";
                        ps.println("2");
                         b.setStyle("-fx-background-image: url('"+text+"');");
                         b.setDisable(true);
                        }

                        if(Game.moves(game.Board)==p1.shape)
                            p1.is_win=1;
                        ps.println(DisplayCharArr(game.Board));
                        // int currentPoss=(indx_x*3)+indx_y;
                        // myDB.insertStep(maxGameId, p1.playerId, currentPoss);
                    }
                    else if(game.mode==0) { // play with computer game
                        //Game.btnClickedRow = indx_x;
                        //Game.btnClickedCol = indx_y;

                        image = new Image("/o.png", true);
                        imgview = new ImageView(image);
                        imgview.setFitHeight(20);
                        imgview.setFitWidth(20);
                        label1.setGraphic(imgview);
                        label1.setTextFill(Color.web("#d42121"));

                        //System.out.println("Your move: ");
                        Point userMove = new Point(indx_x, indx_y);
                        System.out.println("point: "+userMove.toString());

                        boardAI.placeAMove(userMove, 2); //2 for O and O is the user
                        boardAI.displayBoard();

                        int currentPoss=(indx_x*3)+indx_y;
                        myDB.insertStep(maxGameId, p2.playerId, currentPoss);
                        counter++;

                        if (!boardAI.isGameOver()) {
                            if(boardAI.levelOfIntelligence == 2) {
                                System.out.println("level of intellgince = 2");
                                boardAI.minimax(0, 1);
                            }
                            else if(boardAI.levelOfIntelligence == 1) {
                                System.out.println("level of intellgince = 1");
                                int P = 85; //some probability in percent form
                                int randP = ThreadLocalRandom.current().nextInt(0, 100);
                                // take the optimal action 85%% of the time
                                if(randP <= P) { //
                                    boardAI.minimax(0, 1);
                                }
                                // take random action 15% of the time
                                else {
                                   int randomNum = ThreadLocalRandom.current().nextInt(0, 9);
                                    Node target = btns.get(randomNum);
                                    while(target.isDisabled()) {
                                        //System.out.println("Disabled");
                                        randomNum = ThreadLocalRandom.current().nextInt(0, 9);
                                        target = btns.get(randomNum);
                                        //System.out.println("Random move   : "+randomNum);
                                    }
                                   Point p = new Point(randomNum/3, randomNum%3);
                                   boardAI.computersMove = p;
                                   System.out.println(boardAI.computersMove.toString());
                                }
                            }
                            else if(boardAI.levelOfIntelligence == 0) {
                                System.out.println("level of intellgince = 0");
                                int randomNum = ThreadLocalRandom.current().nextInt(0, 9);
                                    Node target = btns.get(randomNum);
                                    while(target.isDisabled()) {
                                        //System.out.println("Disabled");
                                        randomNum = ThreadLocalRandom.current().nextInt(0, 9);
                                        target = btns.get(randomNum);
                                        //System.out.println("Random move   : "+randomNum);
                                    }
                                Point p = new Point(randomNum/3, randomNum%3);
                                //Point p = new Point(rand.nextInt(3), rand.nextInt(3));
                                boardAI.computersMove = p;
                                System.out.println(boardAI.computersMove.toString());
                            }

                            boardAI.placeAMove(boardAI.computersMove, 1);
                            boardAI.displayBoard();
                            currentPoss=(boardAI.computersMove.x*3)+boardAI.computersMove.y;
                            myDB.insertStep(maxGameId, p1.playerId, currentPoss);

                            image = new Image("/x.png", true);
                            imgview = new ImageView(image);
                            imgview.setFitHeight(20);
                            imgview.setFitWidth(20);
                            label1.setGraphic(imgview);
                            label1.setTextFill(Color.web("#0076a3"));

                            counter++;
                        }
                        else {
                            System.out.println("game Over");
                            System.out.println("you won");
                        }
                    } // two players locally
                    else if(counter%2==0&&game.mode == 1){   // even
                        //set label and image to current player
                        image = new Image("/o.png", true);
                        imgview = new ImageView(image);
                        imgview.setFitHeight(20);
                        imgview.setFitWidth(20);
                        label1.setGraphic(imgview);
                        label1.setTextFill(Color.web("#d42121"));
                        System.out.println("even");
                        text="x.png";
                        player=1;
                        movesPlayer1[indx_x][indx_y]=1;
                        p1.is_win=p1.moves(movesPlayer1);
                        Display(movesPlayer1);
                        text ="x.png";
                        b.setStyle("-fx-background-image: url('"+text+"');");
                        b.setDisable(true);
                        int currentPoss=(indx_x*3)+indx_y;
                        myDB.insertStep(maxGameId, p1.playerId, currentPoss);
                        counter++;
                        //Game.btnClickedRow = indx_x;
                        //Game.btnClickedCol = indx_y;

                    }else if(game.mode == 1&&counter%2!=0) {      // odd 2-players locally
                        //set label and image to current player
                        image = new Image("/x.png", true);
                        imgview = new ImageView(image);
                        imgview.setFitHeight(20);
                        imgview.setFitWidth(20);
                        label1.setGraphic(imgview);
                        label1.setTextFill(Color.web("#0076a3"));

                        System.out.println("odd");
                        player=2;
                        movesPlayer2[indx_x][indx_y]=1;
                        p2.is_win=p2.moves(movesPlayer2);
                        Display(movesPlayer2);
                        text="o.png";
                        b.setStyle("-fx-background-image: url('"+text+"');");
                        b.setDisable(true);
                        int currentPoss=(indx_x*3)+indx_y;
                        myDB.insertStep(maxGameId, p2.playerId, currentPoss);
                        counter++;
                        //Game.btnClickedRow = indx_x;
                        //Game.btnClickedCol = indx_y;
                    }


                System.out.println("game mode: "+game.mode);
                System.out.println("\n");
                System.out.println(p1.is_win+" "+p2.is_win);


                if(game.mode==0&&boardAI.isGameOver()) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    mediaPlayer.stop();
                    if (boardAI.hasXWon()) {
                        System.out.println("Unfortunately, you lost!");
                        alert.setTitle("Lose");
                        alert.setHeaderText(p1.name+" is Winner");
                        alert.setContentText("Do you want play again ?");
                         Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            boardAI = new BoardAI();
                            counter = 0;
                            start(stage);
                        }else{
                            Platform.exit();
                        }
                    } else if (boardAI.hasOWon()) {
                        System.out.println("You win!");
                        alert.setTitle("Win");
                        alert.setHeaderText(p2.name+" is Winner");
                        alert.setContentText("Do you want play A video as gift?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                                   StackPane root = new StackPane();

                                   MediaPlayer player = new MediaPlayer( new Media(getClass().getResource("nw.mp4").toExternalForm()));
                                   MediaView mediaView = new MediaView(player);

                                   root.getChildren().add( mediaView);
                                   Scene scene = new Scene(root,500,500);
                                   Stage primaryStage=new Stage();
                                   primaryStage.setScene(scene);
                                   primaryStage.show();
                                   player.play();
                                   System.out.println("Ok");

                            }else{
                                    Platform.exit();
                            }
                    } else {
                        System.out.println("It's a draw!");
                        alert.setTitle("Tie");
                        alert.setHeaderText("It's a tie!");
                        alert.setContentText("Do you want play again ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            boardAI = new BoardAI();
                            counter = 0;
                            start(stage);
                        }else{
                            Platform.exit();
                        }
                    }


                }
                if(p1.is_win==1&&game.mode!=0){
                    if(game.mode==2)
                           ps.println("@"+p1.name);
                    //else

                    showWinner(p1.name);
                }else if(p2.is_win==1&&game.mode!=0){
                     DisplayCharArr(game.Board);
                     showWinner(p2.name);
                }
                else if(counter == 9&&game.mode!=0) {
                    Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Tie");
                    alert.setHeaderText("It's a tie!");
                    alert.setContentText("Do you want play again ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        mediaPlayer.stop();
                        p1 = new Player();
                        p2 = new Player();
                        movesPlayer1= p1.movesPlayer;
                        movesPlayer2= p2.movesPlayer;
                        counter = 0;
                        start(stage);
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
                 stage.setScene(firstScene(stage));
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
    public int mode; //0 withpc , 1 locally , 2 network mode
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
          if(((positions[0][0]=='o' && positions[1][1]=='o')&&positions[2][2]=='o')||((positions[0][2]=='o' && positions[1][1]=='o')&&positions[2][0]=='o'))
                return 'o';



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


//AI Code

class Point {

    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}

class PointAndScore {

    int score;
    Point point;

    PointAndScore(int score, Point point) {
        this.score = score;
        this.point = point;
    }
}

class BoardAI {
    List<Point> availablePoints;
    Scanner scan = new Scanner(System.in);


    int[][] board = new int[3][3];


    // 0 for blind move, 1 for novice , 2 for master move
    public int levelOfIntelligence = 2;


    public BoardAI() {
    }

    public boolean isGameOver() {
        //Game is over is someone has won, or board is full (draw)
        return (hasXWon() || hasOWon() || getAvailableStates().isEmpty());
    }

    public boolean hasXWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 1) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 1)) {
            //System.out.println("X Diagonal Win");
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if (((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 1)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 1))) {
                // System.out.println("X Row or Column win");
                return true;
            }
        }
        return false;
    }

    public boolean hasOWon() {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 2) || (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 2)) {
            // System.out.println("O Diagonal Win");
            return true;
        }
        for (int i = 0; i < 3; ++i) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 2)
                    || (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == 2)) {
                //  System.out.println("O Row or Column win");
                return true;
            }
        }

        return false;
    }

    public List<Point> getAvailableStates() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == 0) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }
        return availablePoints;
    }

    public void placeAMove(Point point, int player) {
        board[point.x][point.y] = player;//player = 1 for X, 2 for O
        //System.out.println("PlaceAMove : point="+point.toString());
    }

    void takeHumanInput() {
        System.out.println("Your move: ");
        int x = scan.nextInt();
        int y = scan.nextInt();
        Point point = new Point(x, y);
        placeAMove(point, 2);
    }

    public void displayBoard() {
        System.out.println("Display Board: ");

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if(board[i][j]==1) { // o if computer is number 1 , x otherwise
                    Tictactoe_project.btns.get(i*3+j).setDisable(true);
                    Tictactoe_project.btns.get(i*3+j).setStyle("-fx-background-image: url('o.png')");
                }
                else if(board[i][j]==2) { //x if computer is number 1 , o otherwise
                    Tictactoe_project.btns.get(i*3+j).setDisable(true);
                    Tictactoe_project.btns.get(i*3+j).setStyle("-fx-background-image: url('x.png')");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();

        }
    }

    //0 for dumb , 1 for novice , 2 for master
    Point computersMove;

    public int minimax(int depth, int turn) {
        if (hasXWon()) return +1;
        if (hasOWon()) return -1;

        List<Point> pointsAvailable = getAvailableStates();
        if (pointsAvailable.isEmpty()) return 0;


        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

        for (int i = 0; i < pointsAvailable.size(); ++i) {
            Point point = pointsAvailable.get(i);
            if (turn == 1) {
                placeAMove(point, 1);
                int currentScore = minimax(depth + 1, 2);
                max = Math.max(currentScore, max);

                if(depth == 0)System.out.println("Score for position "+(i+1)+" = "+currentScore);
                if(currentScore >= 0){ if(depth == 0) computersMove = point;}
                if(currentScore == 1){board[point.x][point.y] = 0; break;}
                if(i == pointsAvailable.size()-1 && max < 0){if(depth == 0)computersMove = point;}
            } else if (turn == 2) {
                placeAMove(point, 2);
                int currentScore = minimax(depth + 1, 1);
                min = Math.min(currentScore, min);
                if(min == -1){board[point.x][point.y] = 0; break;}
            }
            board[point.x][point.y] = 0; //Reset this point
        }
        return turn == 1?max:min;
    }
}
