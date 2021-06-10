package org.cloud.server.commands;

import org.cloud.common.requests.RequestTypes;


import java.io.Serializable;

public class ReadFileCommand implements Command<Serializable> {

    private final RequestTypes requestType;

    public ReadFileCommand() {
        requestType = RequestTypes.READ_FILE;
    }

    @Override
    public RequestTypes getRequestType() {
        return requestType;
    }

    @Override
    public void setData(Serializable data) {

    }

    @Override
    public Serializable call() {
        return null;
    }
}
