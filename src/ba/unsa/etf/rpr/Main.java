package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import static javafx.application.Application.launch;
import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/glavna.fxml"));
        GlavnaController controler = new GlavnaController();
        loader.setController(controler);
        Parent root = loader.load();
        primaryStage.setTitle("Gradovi i države");
        primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        primaryStage.show();
        //primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);

    }
}
