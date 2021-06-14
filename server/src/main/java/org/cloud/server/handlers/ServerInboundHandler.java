package org.cloud.server.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.cloud.server.commands.executor.CommandExecutor;
import org.cloud.server.commands.factory.DefaultCommandFactory;
import org.cloud.server.messages.Message;

@Slf4j
public class ServerInboundHandler extends SimpleChannelInboundHandler<Message> {
    private CommandExecutor executor;

    public ServerInboundHandler() {
        executor = new CommandExecutor(new DefaultCommandFactory());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message message) {
        log.info("{}", message.getRequestType());
        var command = executor.getCommand(message);
        var result = executor.execute(command);
        message.setStatus(result.getRight());
        message.setData(result.getLeft());
        log.info("{}", result.getRight());
        ctx.fireChannelRead(message);
    }
}
