package org.cloud.server.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.cloud.server.handlers.ConnectionHandler;
import org.cloud.server.handlers.ProtocolMessageToResponse;
import org.cloud.server.handlers.ProtocolRequestToMessage;
import org.cloud.server.handlers.ServerInboundHandler;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // Sequence of handlers from top to bottom
        pipeline.addLast("Decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
        pipeline.addLast("Builder request to message", new ProtocolRequestToMessage());
        pipeline.addLast("Connection handler", new ConnectionHandler());
        pipeline.addLast("Main handler", new ServerInboundHandler());
        pipeline.addLast("Builder message to response", new ProtocolMessageToResponse());
        pipeline.addLast("Chunked write handler", new ChunkedWriteHandler());
        pipeline.addLast("Encoder", new ObjectEncoder());
    }
}
