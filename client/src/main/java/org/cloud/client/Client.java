package org.cloud.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;

@Slf4j
public class Client extends Application {

    @Override
    @SneakyThrows
    public void start(Stage stage) {
        Network.getNetwork().start(stage);
        URL mainFXML = getClass().getResource("/auth.fxml");
        var loader = new FXMLLoader(mainFXML);
        Parent root = loader.load();
        stage.setTitle("Cloudf");
        stage.setScene(new Scene(root));
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }
}
