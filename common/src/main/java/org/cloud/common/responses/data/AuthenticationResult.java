package org.cloud.common.responses.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AuthenticationResult implements Serializable {
    private UUID sessionId;
}
