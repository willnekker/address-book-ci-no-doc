package com.example.addressbook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class for the Address Book application.
 */
public class HelloApplication extends Application {
    // Constants defining the window title and size
    public static final String TITLE = "Address Book";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 360;

    /**
     * Entry point for the JavaFX application.
     * Loads the FXML file, sets up the scene, and displays the main window.
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method to launch the JavaFX application.
     * @param args Command-line arguments (unused).
     */
    public static void main(String[] args) {
        launch();
    }
}