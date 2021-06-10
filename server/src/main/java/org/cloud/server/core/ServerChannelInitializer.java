package org.cloud.server.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.cloud.server.handlers.ConnectionHandler;
import org.cloud.server.handlers.ObjectHandler;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();

        // Sequence of handlers from top to bottom
        pipeline.addLast("Decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
        pipeline.addLast("Connection handler", new ConnectionHandler());
        pipeline.addLast(new ObjectHandler());
        pipeline.addLast("Chunked write handler", new ChunkedWriteHandler());
        pipeline.addLast("Encoder", new ObjectEncoder());
    }
}
