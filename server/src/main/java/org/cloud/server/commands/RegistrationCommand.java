package org.cloud.server.commands;

import io.netty.channel.Channel;
import org.apache.commons.lang3.tuple.Pair;
import org.cloud.common.requests.RequestTypes;
import org.cloud.common.requests.data.RegistrationData;
import org.cloud.common.responses.Status;
import org.cloud.common.responses.data.RegistrationResult;

import java.io.Serializable;

public class RegistrationCommand implements Command<RegistrationResult> {
    private RegistrationData data;
    private Channel channel;

    @Override
    public RequestTypes getRequestType() {
        return RequestTypes.REGISTRATION;
    }

    @Override
    public void setData(Serializable data) {
        this.data = (RegistrationData) data;
    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public Pair<RegistrationResult, Status> call() {
        return null;
    }
}
