package org.cloud.server.services;

import io.netty.channel.Channel;
import lombok.SneakyThrows;
import org.cloud.common.requests.RequestTypes;
import org.cloud.server.conf.Settings;
import org.cloud.server.core.SessionPool;
import org.cloud.server.models.context.AccountContext;
import org.cloud.common.requests.data.RegistrationData;

import java.io.Serializable;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.UUID;

public class RegistrationService implements Service<UUID> {
    private RegistrationData data;
    private Channel channel;
    private Properties settings = Settings.getSettings();

    @Override
    public RequestTypes getType() {
        return RequestTypes.REGISTRATION;
    }

    @Override
    public void setData(Serializable data) {
        this.data = (RegistrationData) data;
    }

    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    @SneakyThrows
    public UUID call() {
        var context = new AccountContext();
        context.create(data.getFirstName(), data.getLastName(),
                data.getNick(), data.getLogin(), data.getPassword());
        createPathFromLogin(data.getLogin());
        var account = context.findByLogin(data.getLogin());
        var sessionPool = SessionPool.getSessionPool();
        return sessionPool.createSession(channel, account.getId());
    }

    private void createPathFromLogin(String login) {
        Paths.get(settings.getProperty("serverPath"), login).toFile().mkdir();
    }
}
