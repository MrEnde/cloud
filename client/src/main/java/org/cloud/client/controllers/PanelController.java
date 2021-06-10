package org.cloud.client.controllers;

import org.cloud.client.info.FileInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class PanelController implements Initializable {
    @FXML
    TableView<FileInfo> filesTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void selectDiskAction(ActionEvent actionEvent) {

    }

    public void btnPathUpAction(ActionEvent actionEvent) {

    }
}
