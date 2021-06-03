package org.cloud.server.models.utils;

import java.util.ArrayList;

import lombok.extern.log4j.Log4j;
import org.cloud.server.models.Model;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

@Log4j
public class DbSessionsFactory {
    private static volatile SessionFactory _sessionFactory;

    public static SessionFactory getSessionFactory() {
        var result = _sessionFactory;

        if (result != null) {
            return result;
        }

        synchronized(DbSessionsFactory.class) {
            if (_sessionFactory == null) {
                try {
                    var configuration = new Configuration().configure();
                    getModels().forEach(configuration::addAnnotatedClass);
                    var builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                    _sessionFactory = configuration.buildSessionFactory(builder.build());

                } catch (Exception e) {
                    log.error("Error build db session: ", e);
                }
            }
            return _sessionFactory;
        }

    }

    private static ArrayList<Class<? extends Model>> getModels() {
        return new ArrayList<>(new Reflections("org/cloud/server/model").getSubTypesOf(Model.class));
    }

}
