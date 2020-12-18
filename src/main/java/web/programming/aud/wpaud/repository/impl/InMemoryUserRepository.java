package web.programming.aud.wpaud.repository.impl;

import org.springframework.stereotype.Repository;
import web.programming.aud.wpaud.bootstrap.DataHolder;
import web.programming.aud.wpaud.model.User;

import java.util.Optional;

@Repository
public class InMemoryUserRepository {

    public Optional<User> findUserByUsername(String username) {

        return DataHolder.users.stream().filter(i -> i.getUsername().equals(username)).findFirst();
    }

    public Optional<User> findByUsernameAndPassword(String username, String password) {

        return DataHolder.users.stream().filter(i -> i.getUsername().equals(username) || i.getPassword().equals(password)).findFirst();
    }

    public User saveOrUpdate(User user) {
        DataHolder.users.removeIf(i -> i.getUsername().equals(user.getUsername()));
        DataHolder.users.add(user);
        return user;
    }

    public void delete(String username) {

        DataHolder.users.removeIf(i -> i.getUsername().equals(username));
    }
}
