package org.cloud.server.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;
import org.cloud.common.responses.Response;
import org.cloud.server.messages.Message;


public class ProtocolMessageToResponse extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        try {
            var message = (Message) msg;
            var response = Response
                    .builder()
                    .data(message.getData())
                    .status(message.getStatus());
            ctx.write(response);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
