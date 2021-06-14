package org.cloud.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.cloud.client.Network;
import org.cloud.client.Utils;
import org.cloud.client.validators.*;
import org.cloud.common.requests.Request;
import org.cloud.common.requests.RequestTypes;
import org.cloud.common.requests.data.AuthenticationData;
import org.cloud.common.requests.data.RegistrationData;

import java.net.URL;
import java.util.ResourceBundle;

public class RegController implements Initializable {
    @FXML
    public TextField loginField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public TextField firstnameField;

    @FXML
    public TextField lastnameField;

    @FXML
    public TextField emailField;

    @FXML
    public TextField nickField;

    @FXML
    public Button buttonLogIn;

    @FXML
    public Button buttonSingUp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        emailField.textProperty().addListener(event -> Utils.setPseudoClassForField(emailField, new EmailValidator(emailField.getText()).isValid()));
        loginField.textProperty().addListener(event -> Utils.setPseudoClassForField(loginField, new LoginValidator(loginField.getText()).isValid()));
        passwordField.textProperty().addListener(event -> Utils.setPseudoClassForField(passwordField, new PasswordValidator(passwordField.getText()).isValid()));
        firstnameField.textProperty().addListener(event -> Utils.setPseudoClassForField(firstnameField, new FirstnameValidator(firstnameField.getText()).isValid()));
        lastnameField.textProperty().addListener(event -> Utils.setPseudoClassForField(lastnameField, new LastnameValidator(lastnameField.getText()).isValid()));
        nickField.textProperty().addListener(event -> Utils.setPseudoClassForField(nickField, new NickValidator(nickField.getText()).isValid()));
    }

    @SneakyThrows
    public void changeController(ActionEvent actionEvent) {
        Parent auth = new FXMLLoader(getClass().getResource("/auth.fxml")).load();
        var stage = (Stage) buttonSingUp.getScene().getWindow();
        stage.setScene(new Scene(auth));
    }

    public void registration(ActionEvent actionEvent) {
        // TODO: implement validation
        var data = new RegistrationData(firstnameField.getText(), lastnameField.getText(),
                nickField.getText(), loginField.getText(),
                passwordField.getText(), emailField.getText()); // TODO: implement the builder pattern
        Network.getNetwork().getConnection().send(new Request(RequestTypes.REGISTRATION, null, data));
    }
}
