package org.cloud.server.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import lombok.extern.slf4j.Slf4j;
import org.cloud.common.requests.RequestTypes;
import org.cloud.server.messages.Message;

import org.cloud.common.responses.Response;
import org.cloud.common.responses.Status;

import org.cloud.server.core.SessionPool;

@Slf4j
public class ConnectionHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.debug("Connected: {}", ctx.channel().remoteAddress());
        // ctx.writeAndFlush(new Response(Status.UNAUTHORIZED));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.debug("Disconnected: {}", ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) {
        var sessionId = msg.getSessionId();
        log.info("{}", msg.getRequestType());
        if (sessionId != null) {
            var sessionPool = SessionPool.getSessionPool();
            if (sessionPool.getSessionOrNull(sessionId) != null) {
                ctx.fireChannelRead(msg);
                return;
            }
        }
        var requestType = msg.getRequestType();
        if (requestType.equals(RequestTypes.AUTHENTICATION) || requestType.equals(RequestTypes.REGISTRATION)) {
            ctx.fireChannelRead(msg);
            return;
        }
        log.error("Error");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        var channel = ctx.channel();
        log.error("Connection error: address={} {}", channel.remoteAddress(), cause);
        channel.close();
    }
}
