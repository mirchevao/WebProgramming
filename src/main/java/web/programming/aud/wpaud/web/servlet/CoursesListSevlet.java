package web.programming.aud.wpaud.web.servlet;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import web.programming.aud.wpaud.service.CourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;

@WebServlet(name="course-student-web-servlet",urlPatterns = "/servlet/listCourses")
public class CoursesListSevlet extends HttpServlet {

    private final CourseService courseService;
    private final SpringTemplateEngine springTemplateEngine;

    public CoursesListSevlet(CourseService courseService, SpringTemplateEngine springTemplateEngine) {
        this.courseService = courseService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("listAll", courseService.listAll());
        this.springTemplateEngine.process("listCourses.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String selected = (String) req.getParameter("listAll");
        req.getSession().setAttribute("listAll", selected);
        req.getSession().setAttribute("course", courseService.listStudentsByCourse(parseLong(selected)));
        resp.sendRedirect("/servlet/addStudent");
    }
}
