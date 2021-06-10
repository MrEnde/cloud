package org.cloud.server.commands;

import org.cloud.server.commands.factory.CommandFactory;

import java.io.Serializable;

public class CommandInput {
    private CommandFactory<Serializable> _commandFactory;

    public CommandInput(CommandFactory<Serializable> commandFactory) {
        _commandFactory = commandFactory;
    }

    public Command getCommand(Serializable data) {

        return _commandFactory.CreateCommand(data);
    }
}
