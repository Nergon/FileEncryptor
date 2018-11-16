package pw.nergon.fileencryptor;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;

public class Controller {

    @FXML
    private JFXTextField inputFileField;
    @FXML
    private ToggleGroup mode;
    @FXML
    private JFXPasswordField passwordField;

    @FXML
    public void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select file to en/decrypt");
        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null)
            inputFileField.setText(selectedFile.getAbsolutePath());
        /*try {
            inputFileField.setText(chooser.getSelectedFile().getAbsolutePath());
        } catch (Throwable t) {
            t.printStackTrace();
        }*/
    }

    @FXML
    public void handleStartClick() {
        if(mode.getSelectedToggle() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Please Select a mode");
            alert.setHeaderText("Please select a mode");
            alert.setContentText("Please select if you want to en- or decrypt the File");
            alert.showAndWait();
            return;
        }

        if(inputFileField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Please choose a File");
            alert.setHeaderText("Please choose a File");
            alert.setContentText("Please choose a File");
            alert.showAndWait();
            return;
        }

        if(passwordField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Please choose a Password");
            alert.setHeaderText("Please choose a Password");
            alert.setContentText("Please choose a password");
            alert.showAndWait();
            return;
        }

        FileHandler fileHandler = new FileHandler(inputFileField.getText(), mode.getSelectedToggle().getUserData().toString(), passwordField.getText());
        fileHandler.switchMode();

    }

}
