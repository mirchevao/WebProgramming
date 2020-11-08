package web.programming.aud.wpaud.service.impl;

import org.springframework.stereotype.Service;
import web.programming.aud.wpaud.model.Course;
import web.programming.aud.wpaud.model.Student;
import web.programming.aud.wpaud.repository.CourseRepository;
import web.programming.aud.wpaud.service.CourseService;
import web.programming.aud.wpaud.service.StudentService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentService studentService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        return courseRepository.addStudentToCourse(new Student(username), new Course(courseId));
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }
}
