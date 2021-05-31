package org.cloud.server.commands.factory;

import org.cloud.server.commands.Command;

import java.util.List;

public interface CommandFactory<E> {
    List<Command> GetCommands();
    Command CreateCommand(E message);
}
