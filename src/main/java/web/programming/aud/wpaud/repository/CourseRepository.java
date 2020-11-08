package web.programming.aud.wpaud.repository;

import org.attoparser.trace.MarkupTraceEvent;
import org.springframework.stereotype.Repository;
import web.programming.aud.wpaud.bootstrap.DataHolder;
import web.programming.aud.wpaud.model.Course;
import web.programming.aud.wpaud.model.Student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Repository
public class CourseRepository {
    public List<Course> findAllCourses() {
        return DataHolder.courses;
    }

    public Optional<Course> findById(Long courseId) {
      return DataHolder.courses.stream().filter(r -> r.getCourseId().equals(courseId)).findFirst();
    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
       Optional<Course> course = findById(courseId);
       return DataHolder.students.stream().filter(r -> r.getName().contains(course.toString())).collect(Collectors.toList());
    }

    public Course addStudentToCourse(Student student, Course course) {
        if (student==null || student.getName().isEmpty() || course==null || course.getName().isEmpty()) {
            return null;
        }

        DataHolder.courses.removeIf(r -> r.getName().equals(course.getName()));
        DataHolder.courses.add(course);
        return course;
    }
}
