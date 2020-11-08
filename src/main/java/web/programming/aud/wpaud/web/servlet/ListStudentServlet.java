package web.programming.aud.wpaud.web.servlet;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import web.programming.aud.wpaud.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Long.parseLong;

@WebServlet(name="student-course-web-servlet",urlPatterns = "/servlet/АddStudent")
public class ListStudentServlet extends HttpServlet {

    private final StudentService studentService;
    private final SpringTemplateEngine springTemplateEngine;

    public ListStudentServlet(StudentService studentService, SpringTemplateEngine springTemplateEngine) {
        this.studentService = studentService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("listStudents", studentService.listAll());
        this.springTemplateEngine.process("listStudents.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selected = (String) req.getParameter("listStudents");
        req.getSession().setAttribute("listAll", selected);
        req.getSession().setAttribute("course", studentService.listAll());
        resp.sendRedirect("/StudentEnrollmentSummary");
    }
}
