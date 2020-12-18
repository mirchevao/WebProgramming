package web.programming.aud.wpaud.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.programming.aud.wpaud.model.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
