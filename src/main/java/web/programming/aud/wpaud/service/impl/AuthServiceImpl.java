package web.programming.aud.wpaud.service.impl;

import org.springframework.stereotype.Service;
import web.programming.aud.wpaud.model.User;
import web.programming.aud.wpaud.model.exceptions.InvalidArgumentException;
import web.programming.aud.wpaud.model.exceptions.InvalidUserCredentialsException;
import web.programming.aud.wpaud.model.exceptions.PasswordsDoNotMatchException;
import web.programming.aud.wpaud.repository.InMemoryUserRepository;
import web.programming.aud.wpaud.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final InMemoryUserRepository inMemoryUserRepository;

    public AuthServiceImpl(InMemoryUserRepository inMemoryUserRepository) {

        this.inMemoryUserRepository = inMemoryUserRepository;

    }

    @Override
    public User login(String username, String password) {

        if(username == null && username.isEmpty() && password == null && password.isEmpty()) {

            throw new InvalidArgumentException();
        }

        return inMemoryUserRepository.findByUsernameAndPassword(username,password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String name, String surname, String username, String password, String repeatPassword) {
        if(username == null && username.isEmpty() && password == null && password.isEmpty()) {

            throw new InvalidArgumentException();
        }
        if(!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }
        User user = new User(username,password,repeatPassword,name,surname);
        inMemoryUserRepository.saveOrUpdate(user);
        return user;
    }
}
