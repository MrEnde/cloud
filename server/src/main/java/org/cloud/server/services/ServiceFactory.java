package org.cloud.server.services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.cloud.common.requests.RequestTypes;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ServiceFactory {
    private volatile List<Service> _services;

    @SneakyThrows
    public List<Service> GetServices() {
        var result = _services;

        if (result != null) {
            return result;
        }

        synchronized(ServiceFactory.class) {
            if (_services == null) {
                try {
                    _services = new ArrayList<>();
                    for (var service : new Reflections("org/cloud/server/services").getSubTypesOf(Service.class)) {
                        _services.add(service.getDeclaredConstructor().newInstance());
                    }
                } catch (Exception e) {
                    log.error("Error create List<Service>: ", e);
                }
            }
            return _services;
        }
    }

    public Service getService(RequestTypes type) {
        for (var service : GetServices()) {
            if(service.getType().equals(type)) {
                return service;
            }
        }
        return null;
    }
}
