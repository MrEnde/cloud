package org.cloud.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.Getter;
import lombok.Setter;
import org.cloud.common.requests.Request;

import java.util.UUID;

public class Connection {
    private final Channel channel;

    @Getter
    @Setter
    private UUID sessionId;

    public Connection(Channel channel) {
        this.channel = channel;
    }

    protected Channel getChannel() {
        return channel;
    }

    public void send(Request packet) {
        channel.writeAndFlush(packet);
    }

    public boolean isConnected() {
        return channel.isOpen() && channel.isActive();
    }

    public ChannelFuture close() {
        return channel.close();
    }
}
