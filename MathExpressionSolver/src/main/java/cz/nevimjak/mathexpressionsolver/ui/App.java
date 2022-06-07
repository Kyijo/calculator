package cz.nevimjak.mathexpressionsolver.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {

    public static void run() {
        launch();
    }

    /**
     * Načtení a zobrazení uživatelského rozhraní
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/calculator-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 360);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
