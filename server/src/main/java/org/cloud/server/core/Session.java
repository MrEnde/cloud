package org.cloud.server.core;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.Data;

import java.util.UUID;

@Data
public class Session {
    private final UUID id;
    private final Channel channel;
    private final Long userId;

    private boolean closed;

    public Session(Channel channel, Long userId) {
        this.channel = channel;
        this.userId = userId;
        this.id = UUID.randomUUID();
    }

    public ChannelFuture close() {
        closed = true;
        return channel.close();
    }

    public boolean isConnected() {
        return !closed && channel.isOpen() && channel.isActive();
    }

}
