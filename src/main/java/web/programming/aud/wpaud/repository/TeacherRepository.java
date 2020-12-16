package web.programming.aud.wpaud.repository;

import org.springframework.stereotype.Repository;
import web.programming.aud.wpaud.bootstrap.DataHolder;
import web.programming.aud.wpaud.model.Teacher;

import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository {

    public List<Teacher> findAll() {
        return DataHolder.teachers;
    }

    public Optional<Teacher> findById(Long id) {

        return DataHolder.teachers.stream().filter(i -> i.getId().equals(id)).findFirst();
    }


}
