package org.cloud.common.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class Response implements Serializable {
    private Status status;
    private Serializable data;

    public Response(Status status) {
        this.status = status;
    }
}
