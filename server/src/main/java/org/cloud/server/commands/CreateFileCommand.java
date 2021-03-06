package org.cloud.server.commands;

import io.netty.channel.Channel;
import org.apache.commons.lang3.tuple.Pair;
import org.cloud.common.requests.RequestTypes;
import org.cloud.common.responses.Status;

import java.io.Serializable;

public class CreateFileCommand implements Command<Serializable> {
    private Channel channel;

    @Override
    public RequestTypes getRequestType() {
        return RequestTypes.CREATE_FILE;
    }

    @Override
    public void setData(Serializable data) {

    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public Pair<Serializable, Status> call() {
        return null;
    }
}
