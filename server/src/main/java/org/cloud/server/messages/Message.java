package org.cloud.server.messages;

import org.cloud.common.requests.RequestTypes;

import java.io.Serializable;
import java.util.UUID;

public interface Message extends Serializable {
    void setData(Serializable data);
    Serializable getData();

    void setSessionId(UUID id);
    UUID getSessionId();

    RequestTypes getRequestType();
}
