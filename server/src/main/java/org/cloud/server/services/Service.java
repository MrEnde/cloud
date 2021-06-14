package org.cloud.server.services;

import io.netty.channel.Channel;
import org.cloud.common.requests.RequestTypes;

import java.io.Serializable;
import java.util.concurrent.Callable;

public interface Service<R> extends Callable<R> {
    RequestTypes getType();
    void setData(Serializable data);
    void setChannel(Channel channel);
}
