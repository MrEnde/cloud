package org.cloud.server.services;

import io.netty.channel.Channel;
import lombok.SneakyThrows;
import org.cloud.common.requests.RequestTypes;
import org.cloud.common.requests.data.AuthenticationData;
import org.cloud.server.core.SessionPool;
import org.cloud.server.models.context.AccountContext;

import java.io.Serializable;
import java.util.UUID;

public class AuthenticationService implements Service<UUID>  {
    private AuthenticationData data;
    private Channel channel;

    @Override
    public void setData(Serializable data) {
        this.data = (AuthenticationData) data;
    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public RequestTypes getType() {
        return RequestTypes.AUTHENTICATION;
    }

    @Override
    @SneakyThrows
    public UUID call() {
        var context = new AccountContext();
        var account = context.findByLogin(data.getLogin());
        if (!account.getPassword().equals(data.getPassword())) {
            throw new Exception();
        }
        var sessionPool = SessionPool.getSessionPool();
        return sessionPool.createSession(channel, account.getId());
    }

}
