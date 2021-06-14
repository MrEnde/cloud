package org.cloud.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.cloud.client.Network;
import org.cloud.client.Utils;
import org.cloud.client.validators.LoginValidator;
import org.cloud.client.validators.PasswordValidator;
import org.cloud.common.requests.Request;
import org.cloud.common.requests.RequestTypes;
import org.cloud.common.requests.data.AuthenticationData;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {

    @FXML
    TextField loginField;

    @FXML
    TextField passwordField;

    @FXML
    Button buttonLogIn;

    @FXML
    Button buttonSingUp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginField.textProperty().addListener(event -> Utils.setPseudoClassForField(loginField, new LoginValidator(loginField.getText()).isValid()));
        passwordField.textProperty().addListener(event -> Utils.setPseudoClassForField(passwordField, new PasswordValidator(passwordField.getText()).isValid()));
    }

    @SneakyThrows
    public void changeController(ActionEvent actionEvent) {
        Parent reg = new FXMLLoader(getClass().getResource("/reg.fxml")).load();
        var stage = (Stage) buttonSingUp.getScene().getWindow();
        stage.setScene(new Scene(reg));
    }

    public void authenticate(ActionEvent actionEvent) {
        // TODO: implement validation
        var data = new AuthenticationData(loginField.getText(), passwordField.getText());
        Network.getNetwork().getConnection().send(new Request(RequestTypes.AUTHENTICATION, null, data));

    }
}
