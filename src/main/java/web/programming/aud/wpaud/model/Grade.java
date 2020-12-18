package web.programming.aud.wpaud.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Character grade;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;

    public Grade(Character grade, Student student, Course course) {
        this.grade = grade;
        this.student = student;
        this.course = course;
    }

    public Grade() {

    }
}
