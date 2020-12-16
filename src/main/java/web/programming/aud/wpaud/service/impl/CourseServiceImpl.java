package web.programming.aud.wpaud.service.impl;

import org.springframework.stereotype.Service;
import web.programming.aud.wpaud.model.*;
import web.programming.aud.wpaud.model.exceptions.NoTeacherFoundException;
import web.programming.aud.wpaud.repository.CourseRepository;
import web.programming.aud.wpaud.repository.StudentRepository;
import web.programming.aud.wpaud.repository.TeacherRepository;
import web.programming.aud.wpaud.service.CourseService;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
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

    @Override
    public Course save(String name, String desc, Long teacherId) {

        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new NoTeacherFoundException(teacherId));
        return courseRepository.save(new Course(name, desc, teacher));
    }

    @Override
    public void deleteById(Long id) {

        courseRepository.deleteById(id);
    }

    @Override
    public Optional<Course> getById(Long id) {
        return courseRepository.findById(id);
    }


}
