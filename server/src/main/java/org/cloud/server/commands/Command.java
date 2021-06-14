package org.cloud.server.commands;

import java.io.Serializable;
import java.util.concurrent.Callable;

import io.netty.channel.Channel;
import org.apache.commons.lang3.tuple.Pair;
import org.cloud.common.requests.RequestTypes;
import org.cloud.common.responses.Status;

public interface Command<R extends Serializable> extends Callable<Pair<R, Status>> {
    RequestTypes getRequestType();
    void setData(Serializable data);
    void setChannel(Channel channel);

    Pair<R, Status> call();
}
