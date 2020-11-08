package web.programming.aud.wpaud.repository;

import org.springframework.stereotype.Repository;
import web.programming.aud.wpaud.bootstrap.DataHolder;
import web.programming.aud.wpaud.model.Category;
import web.programming.aud.wpaud.model.Student;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {
    public List<Student> findAllStudents() {
        return DataHolder.students;
    }

    public List<Student> findAllByNameOrSurname(String text) {
        return DataHolder.students.stream().filter(r -> r.getName().contains(text) || r.getSurname().contains(text)).collect(Collectors.toList());
    }

    public Student save(Student s) {
        if (s==null || s.getName().isEmpty()) {
            return null;
        }
        DataHolder.students.removeIf(r->r.getName().equals(s.getName()));
        DataHolder.students.add(s);
        return s;
    }

}
