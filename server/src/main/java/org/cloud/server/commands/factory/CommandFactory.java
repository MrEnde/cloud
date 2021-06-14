package org.cloud.server.commands.factory;

import org.cloud.server.commands.Command;

import java.io.Serializable;
import java.util.List;

public interface CommandFactory<E extends Serializable> {
    List<Command<Serializable>> getCommands();
    Command<Serializable> createCommand(E message);
}
