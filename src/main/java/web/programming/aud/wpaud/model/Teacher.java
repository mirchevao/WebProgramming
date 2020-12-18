package web.programming.aud.wpaud.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Teacher {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    public Teacher(String name, String surname) {

        this.name = name;
        this.surname = surname;
    }


    public Teacher() {

    }
}
