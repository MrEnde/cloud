package org.cloud.server.messages;

import lombok.Builder;
import lombok.Data;
import org.cloud.common.requests.RequestTypes;
import org.cloud.common.responses.Status;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
public class Message implements Serializable {
    private UUID sessionId;
    private RequestTypes requestType;
    private Status status;
    private Serializable data;
}
