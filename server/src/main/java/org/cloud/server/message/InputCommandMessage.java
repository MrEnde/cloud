package org.cloud.server.message;

import lombok.Data;

import java.util.Map;

@Data
public class InputCommandMessage implements Message_ {
    private String commandName;
    private Map<String, ?> options;
}
