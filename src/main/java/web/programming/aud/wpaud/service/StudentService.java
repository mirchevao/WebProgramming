package web.programming.aud.wpaud.service;

import web.programming.aud.wpaud.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> listAll();

    List<Student> searchByNameOrSurname(String text);

    Student save(String username, String password, String name, String surname);

}
