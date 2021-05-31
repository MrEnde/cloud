package org.cloud.server.commands;

import java.util.Map;

public class ReadFileCommand implements Command {
    private final String _name = "cat ";

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void setOption(Map<String, ?> options) {

    }

    @Override
    public void execute() {
        System.out.println("Hello");
    }
}
