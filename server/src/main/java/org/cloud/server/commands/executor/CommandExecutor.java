package org.cloud.server.commands.executor;

import org.apache.commons.lang3.tuple.Pair;
import org.cloud.common.responses.Status;
import org.cloud.server.commands.Command;
import org.cloud.server.commands.factory.CommandFactory;
import org.cloud.server.commands.factory.DefaultCommandFactory;

import java.io.Serializable;

public class CommandExecutor {
    private CommandFactory _commandFactory;

    public CommandExecutor(DefaultCommandFactory commandFactory) {
        _commandFactory = commandFactory;
    }

    public Command<Serializable> getCommand(Serializable data) {

        return _commandFactory.createCommand(data);
    }

    public Pair<Serializable, Status> execute(Command<Serializable> command) {
        return command.call();
    }
}
