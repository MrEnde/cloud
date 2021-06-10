package org.cloud.server.commands.factory;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.cloud.server.commands.Command;
import lombok.SneakyThrows;
import org.cloud.server.messages.Message;
import org.reflections.Reflections;


@Slf4j
public class DefaultCommandFactory implements CommandFactory<Message>  {
    private volatile List<Command> _commands;

    @Override
    @SneakyThrows
    public List<Command> GetCommands() {
        var result = _commands;

        if (result != null) {
            return result;
        }

        synchronized (DefaultCommandFactory.class) {
            if (_commands == null) {
                try {
                    _commands = new ArrayList<>();
                    for (var command : new Reflections("org/cloud/server/commands").getSubTypesOf(Command.class)) {
                        _commands.add(command.getDeclaredConstructor().newInstance());
                    }
                } catch (Exception e) {
                    log.error("Error create List<Command> :", e);
                }
            }
            return _commands;
        }
    }

    @Override
    public Command CreateCommand(Message message) {
        for (var command : GetCommands()) {
            if(command.getRequestType().equals(message.getRequestType())) {
                command.setData(message.getData());
                return command;
            }
        }
        return null;
    }
}
