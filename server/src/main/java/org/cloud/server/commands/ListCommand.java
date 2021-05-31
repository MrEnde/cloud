package org.cloud.server.commands;

import java.util.Map;

public class ListCommand implements Command {
    private final String _name = "ls";

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setOption(Map<String, ?> options) {

    }

    @Override
    public void execute() {

    }
}
