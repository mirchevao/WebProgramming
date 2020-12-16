package web.programming.aud.wpaud.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.programming.aud.wpaud.model.*;
import web.programming.aud.wpaud.service.CourseService;
import web.programming.aud.wpaud.service.StudentService;
import web.programming.aud.wpaud.service.TeacherService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    public CourseController(CourseService courseService, TeacherService teacherService, StudentService studentService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }


    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }

        List<Course> courseList = this.courseService.listAll();
        List<Student> students = this.studentService.listAll();
        model.addAttribute("courses", courseList);
        model.addAttribute("students", students);
        return "listCourse";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam Long teacherId,
                             Model model) {

        if(courseService.listAll().stream().filter(i -> i.getName().equals(name)).count() > 0) {
            String error = "This course already exists!";
            List<Teacher> teachers = teacherService.findAll();
            model.addAttribute("error",error);
            model.addAttribute("hasError", true);
            model.addAttribute("teachers", teachers);
            return "edit-course";
        }
        courseService.save(name,description,teacherId);
        return "redirect:/courses";
    }

    /*@PostMapping("/add")
    public String saveCourse(@RequestParam String name, @RequestParam String description, @RequestParam Long teacherId) {
        try {

            Course course = this.courseService.save(name,description,teacherId);
            return "redirect:/courses";
        } catch (RuntimeException exception) {
            return "redirect:/courses?error=" + exception.getMessage();
        }
    }*/

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.courseService.deleteById(id);
        return "redirect:/courses";
    }

    @GetMapping("/add-form/{id}")
    public String getAddCoursePage(@PathVariable Long id, Model model) {
        try {
            Course course = courseService.getById(id).get();
            model.addAttribute("course", course);
            courseService.listAll().remove(course);

            List<Teacher> teachers = teacherService.findAll();
            model.addAttribute("teachers", teachers);
            return "edit-course";
        } catch (Exception e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            model.addAttribute("courses", courseService.listAll());
            return "listCourses";
        }
    }



    /*@GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@RequestParam(required = false) String error,
                                    Model model, Long teacherId, @PathVariable Long courseId) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }
        Course course = this.courseService.getById(courseId).get();
        Teacher teacher = this.teacherService.findById(teacherId).get();
        List<Student> students = this.studentService.listAll();
        model.addAttribute("course", course);
        model.addAttribute("teacher", teacher);
        model.addAttribute("students", students);
        return "add-course";
    }*/

    /*@GetMapping("/add-form/{id}")
    public String getAddCoursePage(@RequestParam(required = false) String error, Model model, @PathVariable Long id) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }
        List<Course> courseList = this.courseService.listAll();
        Teacher teacher = this.teacherService.findById(id).get();
        List<Student> students = this.studentService.listAll();
        model.addAttribute("courses", courseList);
        model.addAttribute("teacher", teacher);
        model.addAttribute("students", students);
        return "add-course";
    }*/

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model) {
        try {
           Course course = courseService.getById(id).get();
           model.addAttribute("course", course);
           courseService.listAll().remove(course);

           List<Teacher> teachers = teacherService.findAll();
           model.addAttribute("teachers", teachers);
           return "add-course";
        } catch (Exception e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            model.addAttribute("courses", courseService.listAll());
            return "listCourses";
        }
    }

}
