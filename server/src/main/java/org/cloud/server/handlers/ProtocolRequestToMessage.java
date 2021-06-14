package org.cloud.server.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.cloud.common.requests.Request;
import org.cloud.server.messages.Message;


public class ProtocolRequestToMessage extends SimpleChannelInboundHandler<Request> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request request) {
        var message = Message
                .builder()
                .requestType(request.getType())
                .sessionId(request.getSessionId())
                .data(request.getData())
                .build();
        ctx.fireChannelRead(message);
    }
}
