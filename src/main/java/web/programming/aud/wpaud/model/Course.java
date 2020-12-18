package web.programming.aud.wpaud.model;

import lombok.Data;
import web.programming.aud.wpaud.model.enumerations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    private String description;

    @ManyToMany
    private List<Student> students;

    @ManyToOne
    private Teacher teacher;

    @Enumerated(EnumType.STRING)
    private Type courseType;

    public Course(String name, String description, List<Student> students, Teacher teacher) {
        this.name = name;
        this.description = description;
        this.students = students;
        this.teacher = teacher;
    }

    public Course(String name, String description, Teacher teacher) {

        this.name = name;
        this.description = description;
        this.teacher = teacher;
    }

    public Course(Long courseId) {
        this.courseId = courseId;
    }

    public Course() {

    }
}
