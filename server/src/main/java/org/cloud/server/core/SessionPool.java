package org.cloud.server.core;

import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionPool {
    private static volatile SessionPool _sessionPool;
    private final ConcurrentMap<UUID, Session> _session = new ConcurrentHashMap<>();

    public static SessionPool getSessionPool() {
        var result = _sessionPool;

        if (result != null) {
            return result;
        }

        synchronized(SessionPool.class) {
            if (_sessionPool == null) {
                try {
                    _sessionPool = new SessionPool();
                } catch (Exception e) {
                    log.error("Error create ConnectionPool: ", e);
                }
            }
            return _sessionPool;
        }
    }

    public UUID createSession(Channel channel, Long userId) {
        var session = new Session(channel, userId);
        save(session);
        return session.getId();
    }

    public void save(Session session) {
        _session.put(session.getId(), session);
    }

    public Session getSessionOrNull(UUID id) {
        return _session.getOrDefault(id,null);
    }

}
