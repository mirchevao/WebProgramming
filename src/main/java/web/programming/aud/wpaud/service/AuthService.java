package web.programming.aud.wpaud.service;

import web.programming.aud.wpaud.model.User;


public interface AuthService {

    User login(String username, String password);

    User register(String name, String surname, String repeatPassword, String username, String password);
}
