package org.cloud.server.utils;

import lombok.extern.log4j.Log4j;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@Log4j
public class DbSessionsFactory {
    private static volatile SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        var result = sessionFactory;

        if (result != null) {
            return result;
        }

        synchronized(DbSessionsFactory.class) {
            if (sessionFactory == null) {
                try {
                    var configuration = new Configuration().configure();
                    configuration.addAnnotatedClass(User.class);
                    configuration.addAnnotatedClass(Auto.class);
                    var builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                    sessionFactory = configuration.buildSessionFactory(builder.build());

                } catch (Exception e) {
                    log.error("Error build db session: ", e);
                }
            }
            return sessionFactory;
        }
    }
}
