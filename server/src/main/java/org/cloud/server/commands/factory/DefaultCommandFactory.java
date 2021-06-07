package org.cloud.server.commands.factory;

import java.util.ArrayList;
import java.util.List;

import org.cloud.server.commands.Command;
import org.cloud.server.commands.CommandNotFound;
import lombok.SneakyThrows;
// import org.cloud.server.requests.InputCommandMessage;
import org.reflections.Reflections;


public class DefaultCommandFactory implements CommandFactory<InputCommandMessage>  {
    private List<Command> _commands = null;

    @Override
    @SneakyThrows
    public List<Command> GetCommands() {
        if (_commands == null) {
            _commands = new ArrayList<>();
            for (var command : new Reflections("org/cloud/server/commands").getSubTypesOf(Command.class)) {
                _commands.add(command.newInstance());
            }
        }
        return _commands;
    }

    @Override
    @SneakyThrows
    public Command CreateCommand(InputCommandMessage message) {
        for (var command : GetCommands()) {
            if(command.getName().equals(message.getCommandName())) {
                command.setOption(message.getOptions());
                return command;
            }
        }
        throw new CommandNotFound();
    }
}
