package web.programming.aud.wpaud.service.impl;

import org.springframework.stereotype.Service;
import web.programming.aud.wpaud.model.Student;
import web.programming.aud.wpaud.repository.impl.IMStudentRepository;
import web.programming.aud.wpaud.repository.jpa.StudentRepository;
import web.programming.aud.wpaud.service.StudentService;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }

    //@Override
    // searchByNameOrSurname(String text) {
      //  return studentRepository.findAllByNameOrSurname(text);
    //}


    @Override
    public Student save(String username, String password, String name, String surname) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty() || name == null || name.isEmpty() || surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Student student = new Student(username,password,name,surname);
        studentRepository.save(student);
        return student;
    }

    @Override
    public Student findByUsername(String username) {
        return studentRepository.findByUsername(username);
    }
}
