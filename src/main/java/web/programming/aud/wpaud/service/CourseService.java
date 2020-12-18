package web.programming.aud.wpaud.service;

import web.programming.aud.wpaud.model.Course;
import web.programming.aud.wpaud.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Student> listStudentsByCourse(Long courseId);

    //Course addStudentInCourse(String username, Long courseId);



    List<Course> listAll();

    Course save(String name, String desc, Long id);

    public void deleteById(Long id);

    public Optional<Course> getById(Long id);

}
