package org.cloud.server;

import java.util.Properties;

import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import lombok.extern.slf4j.Slf4j;

import org.cloud.server.handlers.ObjectHandler;

@Slf4j
public class Application {
    private final Properties _settings;

    public Application(Properties settings) {
        _settings = settings;
    }

    public void start() {
        EventLoopGroup auth = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            var bootstrap = new ServerBootstrap();
            bootstrap.group(auth, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) {
                            channel.pipeline().addLast(
                                    new ObjectEncoder(),
                                    new ChunkedWriteHandler(),
                                    new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                    new ObjectHandler()
                            );
                        }
                    });
            var channelFuture = bootstrap.bind(Integer.parseInt(_settings.getProperty("port"))).sync();
            log.debug("Server started");
            channelFuture.channel().closeFuture().sync(); // block
        } catch (Exception e) {
            log.error("e=", e);
            Thread.currentThread().interrupt();
        } finally {
            auth.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
