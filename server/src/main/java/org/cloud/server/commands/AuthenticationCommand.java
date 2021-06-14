package org.cloud.server.commands;

import io.netty.channel.Channel;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.cloud.common.requests.RequestTypes;
import org.cloud.common.requests.data.AuthenticationData;
import org.cloud.common.responses.Status;
import org.cloud.server.services.AuthenticationService;

import java.io.Serializable;

public class AuthenticationCommand implements Command<Serializable> {
    private AuthenticationData data;
    private Channel channel;

    @Override
    public RequestTypes getRequestType() {
        return RequestTypes.AUTHENTICATION;
    }

    @Override
    public void setData(Serializable data) {
        this.data = (AuthenticationData) data;
    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public Pair<Serializable, Status> call() {
        var service = new AuthenticationService();
        service.setData(data);
        service.setChannel(channel);
        var pair = new MutablePair<Serializable, Status>();
        try {
            var data = service.call();
            pair.setLeft(data);
            pair.setRight(Status.OK);
        } catch (Exception e) {
            pair.setLeft(null);
            pair.setRight(Status.BAD_REQUEST);
        }
        return pair;
    }
}