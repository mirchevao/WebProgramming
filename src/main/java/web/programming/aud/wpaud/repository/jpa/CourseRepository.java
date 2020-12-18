package web.programming.aud.wpaud.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.programming.aud.wpaud.model.Course;
import web.programming.aud.wpaud.model.Student;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {


    List<Student> findAllByCourseId(Long courseId);

   
}
