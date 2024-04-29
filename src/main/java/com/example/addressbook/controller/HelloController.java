package com.example.addressbook.controller;

import com.example.addressbook.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the Hello application.
 */
public class HelloController {
    @FXML
    private TextArea termsAndConditions;
    @FXML
    private CheckBox agreeCheckBox;
    @FXML
    private Button nextButton;

    /**
     * Initializes the controller. Sets the terms and conditions text.
     */
    @FXML
    public void initialize() {
        termsAndConditions.setText("""
Lorem ipsum dolor sit amet, consectetur adipiscing elit,
sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
Eget dolor morbi non arcu risus. Quis lectus nulla at volutpat diam
ut venenatis tellus in. Feugiat in fermentum posuere urna nec tincidunt
praesent semper. Turpis tincidunt id aliquet risus feugiat in.
Libero volutpat sed cras ornare. Facilisi morbi tempus iaculis urna.
Bibendum est ultricies integer quis auctor. Eu augue ut lectus arcu.
Tincidunt tortor aliquam nulla facilisi cras fermentum odio eu.
Gravida neque convallis a cras. Elit ut aliquam purus sit.
Suspendisse ultrices gravida dictum fusce ut placerat.
Integer feugiat scelerisque varius morbi enim nunc.
Amet justo donec enim diam vulputate ut pharetra.
Sapien pellentesque habitant morbi tristique.
Lorem sed risus ultricies tristique nulla aliquet.
Elementum nibh tellus molestie nunc non blandit massa.""");
    }

    /**
     * Event handler for the agree checkbox click event.
     * Enables or disables the next button based on checkbox selection.
     */
    @FXML
    protected void onAgreeCheckBoxClick() {
        boolean accepted = agreeCheckBox.isSelected();
        nextButton.setDisable(!accepted);
    }

    /**
     * Event handler for the next button click event.
     * Redirects to the main view.
     *
     * @throws IOException if an error occurs during loading the FXML file
     */
    @FXML
    protected void onNextButtonClick() throws IOException {
        Stage stage = (Stage) nextButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    /**
     * Event handler for the cancel button click event.
     * Closes the application window.
     */
    @FXML
    protected void onCancelButtonClick() {
        Stage stage = (Stage) nextButton.getScene().getWindow();
        stage.close();
    }
}