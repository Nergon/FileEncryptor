package pw.nergon.fileencryptor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("FileEncryptor - By Nergon");
        primaryStage.setScene(new Scene(root, 452, 563));
        primaryStage.show();
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        System.out.println(System.getProperty("os.name"));
    }


    public static void main(String[] args) {
        launch(args);
    }

}
