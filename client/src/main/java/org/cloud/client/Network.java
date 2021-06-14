package org.cloud.client;

import javafx.stage.Stage;
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
    private static volatile Network network;
    private Connection connection;

    public static Network getNetwork() {
        var result = network;

        if (result != null) {
            return result;
        }

        synchronized (Network.class) {
            if (network == null) {
                try {
                    network = new Network();
                } catch (Exception e) {
                    log.error("Error build network: ", e);
                }
            }
            return network;
        }
    }

    public void start(Stage stage) {
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
                                        new ClientHandler(stage)
                                );
                            }
                        });
                var channelFuture = bootstrap.connect("localhost", 8000).sync();
                connection = new Connection(channelFuture.channel());
                channelFuture.channel().closeFuture().sync(); // block
            } catch (Exception e) {
                log.error("Connection error: ", e);
                Thread.currentThread().interrupt();
            } finally {
                worker.shutdownGracefully();
            }
        }).start();
    }

    public Connection getConnection() {
        return connection;
    }

    public void saveConnection(Connection connection) {
        this.connection = connection;
    }
}
