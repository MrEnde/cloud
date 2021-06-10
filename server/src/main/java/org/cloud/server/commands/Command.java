package org.cloud.server.commands;

import java.io.Serializable;
import java.util.concurrent.Callable;

import org.cloud.common.requests.RequestTypes;

public interface Command<R extends Serializable> extends Callable<R> {
    RequestTypes getRequestType();
    void setData(Serializable data);

    R call();
}
