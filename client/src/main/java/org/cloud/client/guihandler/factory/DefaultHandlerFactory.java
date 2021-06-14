package org.cloud.client.guihandler.factory;

import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloud.client.guihandler.Handler;
import org.cloud.common.responses.Status;
import org.reflections.Reflections;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DefaultHandlerFactory implements HandlerFactory {
    private volatile List<Handler> handlers;

    @Override
    @SneakyThrows
    public List<Handler> getAllHandlers() {
        var result = handlers;

        if (result != null) {
            return result;
        }

        synchronized (DefaultHandlerFactory.class) {
            if (handlers == null) {
                try {
                    handlers = new ArrayList<>();
                    for (var handler : new Reflections("org/cloud/client/guihandlers/").getSubTypesOf(Handler.class)) {
                        handlers.add(handler.getDeclaredConstructor().newInstance());
                    }
                } catch (Exception e) {
                    log.error("Error create List<Handler> :", e);
                }
            }
            return handlers;
        }
    }

    @Override
    public Handler findHandler(Serializable data, Status status, Stage stage) {
        for (var handler : getAllHandlers()) {
            if (handler.getResultType().equals(data.getClass())) {
                handler.setData(data);
                handler.setStage(stage);
                handler.setStatus(status);
                return handler;
            }
        }
        return null;
    }
}
