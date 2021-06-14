package org.cloud.client.guihandler;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.cloud.client.Network;
import org.cloud.common.responses.Status;
import org.cloud.common.responses.data.AuthenticationResult;

import java.io.Serializable;


public class AuthenticationHandler implements Handler {
    private Stage stage;
    private AuthenticationResult data;
    private Status status;

    @Override
    public void setData(Serializable data) {
        this.data = (AuthenticationResult) data;
    }

    @Override
    public Class<? extends Serializable> getResultType() {
        return AuthenticationResult.class;
    }

    @Override
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    @SneakyThrows
    public void run() {
        if (status.equals(Status.OK)) {
            Parent main = new FXMLLoader(getClass().getResource("/main.fxml")).load();
            stage.setScene(new Scene(main));
            var network = Network.getNetwork();
            var connection = network.getConnection();
            connection.setSessionId(data.getSessionId());
            network.saveConnection(connection);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR, "The username you entered does not belong to the account. Please check your username and try again.");
        alert.show();
    }
}
