package web.programming.aud.wpaud.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String username;
    private String password;
    private String name;
    private String surname;


    public Student(String username, String password, String name, String surname) {

        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Student(String username) {
        this.username = username;
    }


    public Student() {

    }
}
