package org.cloud.server.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import requests.Request;

@Slf4j
public class ObjectHandler extends SimpleChannelInboundHandler<Request> {

    @Override
    @SneakyThrows
    protected void channelRead0(ChannelHandlerContext ctx, Request message) {
        log.debug("received: {}", message);
        ctx.writeAndFlush(message);
    }
}
