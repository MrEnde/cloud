package org.cloud.client.guihandler;

import javafx.stage.Stage;
import org.cloud.common.responses.Status;

import java.io.Serializable;

public interface Handler extends Runnable {
    void setData(Serializable data);
    Class<? extends Serializable> getResultType();
    void setStage(Stage stage);
    void setStatus(Status status);
    void run();
}
