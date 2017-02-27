/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winnervideo;

import java.awt.geom.Rectangle2D;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class WinnerVideo extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

//         javafx.geometry.Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        StackPane root = new StackPane();

        MediaPlayer player = new MediaPlayer( new Media(getClass().getResource("nw.mp4").toExternalForm()));
        MediaView mediaView = new MediaView(player);

        root.getChildren().add( mediaView);

//        Scene scene = new Scene(root,visualBounds.getWidth(), visualBounds.getHeight());
        Scene scene = new Scene(root,500,500);

        primaryStage.setScene(scene);
        primaryStage.show();


        player.play();

    }

    
}
