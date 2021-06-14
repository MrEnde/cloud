package org.cloud.common.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class Request implements Serializable {
    private RequestTypes type;
    private UUID sessionId;
    private Serializable data;
}
