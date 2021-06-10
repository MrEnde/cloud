package org.cloud.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloud.client.controllers.MainController;

import java.net.URL;

@Slf4j
public class Client extends Application {

    @Override
    @SneakyThrows
    public void start(Stage stage) {
        URL mainFXML = getClass().getResource("/main.fxml");
        var loader = new FXMLLoader(mainFXML);
        Parent root = loader.load();
        MainController controller = loader.getController();
        stage.setTitle("Cloud");
        stage.setScene(new Scene(root, 1200, 600));
        stage.show();
    }
}
