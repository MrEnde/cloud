package org.cloud.client;

import org.cloud.client.handlers.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Network {

    public Network() {
        new Thread(() -> {
            EventLoopGroup worker = new NioEventLoopGroup();
            try {
                var bootstrap = new Bootstrap();
                bootstrap.group(worker)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(
                                        new ObjectEncoder(),
                                        new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                        new ClientHandler()
                                );
                            }
                        });
                var channelFuture = bootstrap.connect("localhost", 8000).sync();
                channelFuture.channel().closeFuture().sync(); // block
            } catch (Exception e) {
                log.error("Connection error: ", e);
                Thread.currentThread().interrupt();
            } finally {
                worker.shutdownGracefully();
            }
        }).start();
    }
}
