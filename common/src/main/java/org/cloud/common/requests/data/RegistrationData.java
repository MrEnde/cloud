package org.cloud.common.requests.data;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegistrationData implements Serializable {
    private String firstName;
    private String lastName;
    private String nick;
    private String login;
    private String password;
}

