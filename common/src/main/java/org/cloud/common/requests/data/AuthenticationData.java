package org.cloud.common.requests.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class AuthenticationData implements Serializable {
    private String login;
    private String password;
}
