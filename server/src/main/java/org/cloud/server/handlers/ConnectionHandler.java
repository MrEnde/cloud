package org.cloud.server.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import lombok.extern.slf4j.Slf4j;
import org.cloud.common.responses.data.AuthenticationResult;
import org.cloud.server.services.ServiceFactory;

import org.cloud.common.requests.Request;
import org.cloud.common.responses.Response;
import org.cloud.common.responses.Status;

import org.cloud.server.core.SessionPool;

import java.util.UUID;

@Slf4j
public class ConnectionHandler extends SimpleChannelInboundHandler<Request> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.debug("Connected: {}", ctx.channel().remoteAddress());
        ctx.writeAndFlush(new Response(Status.UNAUTHORIZED));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.debug("Disconnected: {}", ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request request) {
        System.out.println(request.getType());
        var sessionId = request.getSessionId();
        if (sessionId != null) {
            var sessionPool = SessionPool.getSessionPool();
            if (sessionPool.getSessionOrNull(sessionId) != null) {
                ctx.fireChannelRead(request);
                return;
            }
        }
        var service = new ServiceFactory().getService(request.getType());
        if (service == null) {
            ctx.writeAndFlush(new Response(Status.REQUEST_NOT_ALLOWED));
            return;
        }

        service.setData(request.getData());
        service.setChannel(ctx.channel());
        try {
            var newSessionId = (UUID) service.call();
            ctx.writeAndFlush(new Response(Status.CREATED, new AuthenticationResult(newSessionId)));
        } catch (Exception e) {
            ctx.write(new Response(Status.NOT_ACCEPTABLE));
            ctx.write(new Response(Status.UNAUTHORIZED));
            ctx.flush();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        var channel = ctx.channel();
        log.error("Connection error: address={} {}", channel.remoteAddress(), cause);
        channel.close();
    }
}
