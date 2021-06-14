package org.cloud.client.guihandler.factory;

import javafx.stage.Stage;
import org.cloud.client.guihandler.Handler;
import org.cloud.common.responses.Status;

import java.io.Serializable;
import java.util.List;

public interface HandlerFactory {
    List<Handler> getAllHandlers();
    Handler findHandler(Serializable data, Status status, Stage stage);
}
