package web.programming.aud.wpaud.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.programming.aud.wpaud.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    //public List<Student> findAllByNameOrSurname(String text);
    Student findByUsername(String username);
}
