package org.cloud.server.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloud.server.message.Message;

@Slf4j
public class ObjectHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    @SneakyThrows
    protected void channelRead0(ChannelHandlerContext ctx, Message message) {
        log.debug("received: {}", message);
        ctx.writeAndFlush(message);
    }
}
