package web.programming.aud.wpaud.bootstrap;


import lombok.Getter;
import org.springframework.stereotype.Component;
import web.programming.aud.wpaud.model.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
@Getter
public class DataHolder {

    public static List<Category> categories = new ArrayList<>();
    public static List<Student> students = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();
    public static List<User> users = new ArrayList<User>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();

    @PostConstruct
    public void init() {

        categories.add(new Category("Movies", "Movies category"));
        categories.add(new Category("Books", "Books category"));
        categories.add(new Category("Software", "Software category"));
        students.add((new Student("mirchevao", "bakaprase", "Olgica", "Mircheva")));
        students.add((new Student("omirceva", "trici123", "Marija", "Mircheva")));
        students.add((new Student("171294", "pppddd", "Stefan", "Mircheva")));
        students.add((new Student("mirchevaolgica", "hehebaki", "Kire", "Mircheva")));
        students.add((new Student("princeza", "princezanabelom", "Olgica", "Mircheva")));

        users.add(new User("mirchevao", "ole123", "ole123", "Olgica", "Mircheva"));
        users.add(new User("kostovas", "sandra123", "sandra123", "Sandra", "Kostova"));
        users.add(new User("n.atanasovska", "nadica123", "nadica123", "Nadica", "Atanasovska"));

        Category category = new Category("Sport", "Sports category");
        categories.add(category);

        Manufacturer manufacturer = new Manufacturer("Nike", "NY NY");
        manufacturers.add(manufacturer);
        manufacturers.add(new Manufacturer("Apple", "LA LA"));

        products.add(new Product("Ball", 350.0, 3, category, manufacturer));
        products.add(new Product("Harry Potter", 500.0, 3, category, manufacturer));

        teachers.add(new Teacher("name1", "surname1"));
        teachers.add(new Teacher("name2", "surname2"));
        teachers.add(new Teacher("name3", "surname3"));
        teachers.add(new Teacher("name4", "surname4"));
        teachers.add(new Teacher("name5", "surname5"));
        Teacher teacher = new Teacher("name2", "name3");
        Teacher teacher1 = new Teacher("name3", "nad");
        courses.add(new Course("course1", "desc", students, teacher));
        courses.add(new Course("course2", "desc1", students, teacher1));
        courses.add(new Course("course3", "desc2", students, teacher));


    }
}
