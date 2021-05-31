package org.cloud.server.commands;

import java.util.Map;

public class CreateFileCommand implements Command {
    private final String _name = "touch ";

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
