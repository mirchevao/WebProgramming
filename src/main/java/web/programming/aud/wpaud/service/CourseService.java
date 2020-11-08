package web.programming.aud.wpaud.service;

import web.programming.aud.wpaud.model.Course;
import web.programming.aud.wpaud.model.Student;

import java.util.List;

public interface CourseService {

    List<Student> listStudentsByCourse(Long courseId);

    Course addStudentInCourse(String username, Long courseId);

    List<Course> listAll();

}
