package org.cloud.common.requests.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationData implements Serializable {
    private String login;
    private String password;
}
