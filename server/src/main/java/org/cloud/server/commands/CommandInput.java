package org.cloud.server.commands;

import org.cloud.server.commands.factory.CommandFactory;
//import org.cloud.server;

public class CommandInput {
    private CommandFactory _commandFactory;

    public CommandInput(CommandFactory commandFactory) {
        _commandFactory = commandFactory;
    }

//    public Command getCommand(String input) {
//        var org.cloud.server.message = ParseOptions(input);
//
//        return _commandFactory.CreateCommand(new InputCommandMessage());
//    }
}
