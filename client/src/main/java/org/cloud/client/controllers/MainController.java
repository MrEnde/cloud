package org.cloud.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.cloud.client.Network;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    VBox leftPanel, rightPanel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new Network();
    }

    public void copyFile(ActionEvent actionEvent) {

    }

    public void moveFile(ActionEvent actionEvent) {

    }

    public void deleteFile(ActionEvent actionEvent) {

    }

    public void readFile(ActionEvent actionEvent) {

    }
}
