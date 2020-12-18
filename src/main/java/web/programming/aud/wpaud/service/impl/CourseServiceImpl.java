package web.programming.aud.wpaud.service.impl;

import org.springframework.stereotype.Service;
import web.programming.aud.wpaud.model.*;
import web.programming.aud.wpaud.model.exceptions.NoTeacherFoundException;
import web.programming.aud.wpaud.repository.jpa.CourseRepository;
import web.programming.aud.wpaud.repository.jpa.StudentRepository;
import web.programming.aud.wpaud.repository.jpa.TeacherRepository;
import web.programming.aud.wpaud.service.CourseService;
import web.programming.aud.wpaud.service.StudentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final StudentService studentService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.studentService = studentService;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        return courseRepository.findAllByCourseId(courseId);
    }



    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
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
