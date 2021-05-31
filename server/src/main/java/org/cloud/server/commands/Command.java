package org.cloud.server.commands;

import java.util.Map;

public interface Command {
    String getName();

    void setOption(Map<String, ?> options);
    void execute();
}
