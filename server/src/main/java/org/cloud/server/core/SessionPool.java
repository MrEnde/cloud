package org.cloud.server.core;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionPool {
    private static volatile SessionPool sessionPool;
    private final ConcurrentMap<UUID, Session> session = new ConcurrentHashMap<>();

    public static SessionPool getSessionPool() {
        var result = sessionPool;

        if (result != null) {
            return result;
        }

        synchronized(SessionPool.class) {
            if (sessionPool == null) {
                try {
                    sessionPool = new SessionPool();
                } catch (Exception e) {
                    log.error("Error create ConnectionPool: ", e);
                }
            }
            return sessionPool;
        }
    }

    public UUID createSession(Channel channel, Long userId) {
        var newSession = new Session(channel, userId);
        save(newSession);
        return newSession.getId();
    }

    public void save(Session newSession) {
        session.put(newSession.getId(), newSession);
    }

    public Session getSessionOrNull(UUID id) {
        return session.getOrDefault(id,null);
    }

}
