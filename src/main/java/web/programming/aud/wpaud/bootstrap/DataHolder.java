package web.programming.aud.wpaud.bootstrap;


import lombok.Getter;
import org.springframework.stereotype.Component;
import web.programming.aud.wpaud.model.Category;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class DataHolder {

    public static List<Category> categories = new ArrayList<>();



    @PostConstruct
    public void init() {
        categories.add(new Category("Movies", "Movies category"));
        categories.add(new Category("Books", "Books category"));
        categories.add(new Category("Software", "Software category"));


    }
}
