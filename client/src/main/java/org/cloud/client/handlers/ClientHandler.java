package org.cloud.client.handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloud.client.guihandler.executor.HandlerExecutor;
import org.cloud.client.guihandler.factory.DefaultHandlerFactory;
import org.cloud.common.responses.Response;

@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<Response> {
    private HandlerExecutor executor;
    private Stage stage;

    public ClientHandler(Stage stage) {
        executor = new HandlerExecutor(new DefaultHandlerFactory());
        this.stage = stage;
    }

    @Override
    @SneakyThrows
    public void channelActive(ChannelHandlerContext ctx) {
        log.debug("Connected to: {}", ctx.channel().remoteAddress());
    }

    @Override
    @SneakyThrows
    public void channelInactive(ChannelHandlerContext ctx) {
        log.debug("Disconnected: {}", ctx.channel().remoteAddress());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Response response) {
        var handler = executor.getActiveHandler(response, stage);
        if (handler == null) {
            log.info("{}", response.getStatus());
            return;
        }
        executor.execute(handler);
    }
}
