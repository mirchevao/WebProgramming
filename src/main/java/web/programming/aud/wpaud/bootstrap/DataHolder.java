package web.programming.aud.wpaud.bootstrap;


import lombok.Getter;
import org.springframework.stereotype.Component;
import web.programming.aud.wpaud.model.Category;
import web.programming.aud.wpaud.model.Course;
import web.programming.aud.wpaud.model.Student;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Category> categories = new ArrayList<>();
    public static List<Student> students = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();



    @PostConstruct
    public void init() {
        categories.add(new Category("Movies", "Movies category"));
        categories.add(new Category("Books", "Books category"));
        categories.add(new Category("Software", "Software category"));
        students.add((new Student("mirchevao", "bakaprase", "Olgica", "Mircheva")));
        students.add((new Student("omirceva", "trici123", "Olgica", "Mircheva")));
        students.add((new Student("171294", "pppddd", "Olgica", "Mircheva")));
        students.add((new Student("mirchevaolgica", "hehebaki", "Olgica", "Mircheva")));
        students.add((new Student("princeza", "princezanabelom", "Olgica", "Mircheva")));
        courses.add(new Course(123445L, "Discrete Mathematics", "Glup predmet", students));


    }
}
