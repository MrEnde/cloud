package org.cloud.server.models.context;

import java.util.Date;

import org.cloud.server.models.Account;
import org.cloud.server.models.utils.DbSessionsFactory;


public class AccountContext {
    private void create(String firstName, String lastName, String nick,
                        String login, String password) {
        var account = Account
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .nick(nick)
                .login(login)
                .password(password)
                .dateJoined(new Date())
                .build();
        save(account);
    }

    public Account findById(Long id) {
        try (var session = DbSessionsFactory.getSessionFactory().openSession()) {
            return session.get(Account.class, id);
        }
    }

    public void save(Account user) {
        try (var session = DbSessionsFactory.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
    }

    public void update(Account user) {
        try (var session = DbSessionsFactory.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
    }

    public void delete(Account user) {
        try (var session = DbSessionsFactory.getSessionFactory().openSession()) {
            var transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        }
    }

}