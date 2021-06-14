package org.cloud.client.guihandler.executor;

import javafx.stage.Stage;
import org.cloud.client.guihandler.Handler;
import org.cloud.client.guihandler.factory.HandlerFactory;
import org.cloud.common.responses.Response;


public record HandlerExecutor(HandlerFactory factory) {

    public Handler getActiveHandler(Response response, Stage stage) {
        return factory.findHandler(response.getData(), response.getStatus(), stage);
    }

    public void execute(Handler handler) {
        handler.run();
    }
}
