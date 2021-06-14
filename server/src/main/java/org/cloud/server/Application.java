package org.cloud.server;

import java.util.Properties;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import org.cloud.server.core.ServerChannelInitializer;

@Slf4j
public class Application {
    private final Properties _settings;

    public Application(Properties settings) {
        _settings = settings;
    }

    public void start() {
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            var bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerChannelInitializer());
            var channelFuture = bootstrap.bind(Integer.parseInt(_settings.getProperty("port", "8000"))).sync();
            log.debug("Server started");
            channelFuture.channel().closeFuture().sync(); // block
        } catch (Exception e) {
            log.error("e=", e);
            Thread.currentThread().interrupt();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
