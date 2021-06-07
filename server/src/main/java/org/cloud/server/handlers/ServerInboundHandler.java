package org.cloud.server.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerInboundHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.debug("Client connected");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.debug("Client disconnected");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        log.debug("buf: {}", buf);
        StringBuilder sb = new StringBuilder();
        while (buf.isReadable()) {
            sb.append((char) buf.readByte());
        }
        log.debug("received: {}", sb);
        ctx.fireChannelRead(sb.toString());
    }
}
