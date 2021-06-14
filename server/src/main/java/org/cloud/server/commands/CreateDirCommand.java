package org.cloud.server.commands;

import io.netty.channel.Channel;
import org.apache.commons.lang3.tuple.Pair;
import org.cloud.common.requests.RequestTypes;
import org.cloud.common.responses.Status;

import java.io.Serializable;

public class CreateDirCommand implements Command<Serializable> {
    private final RequestTypes requestType;

    public CreateDirCommand() {
        requestType = RequestTypes.CREATE_DIR;
    }

    @Override
    public RequestTypes getRequestType() {
        return requestType;
    }

    @Override
    public void setData(Serializable data) {

    }

    @Override
    public void setChannel(Channel channel) {

    }

    @Override
    public Pair<Serializable, Status> call() {
        return null;
    }
}
