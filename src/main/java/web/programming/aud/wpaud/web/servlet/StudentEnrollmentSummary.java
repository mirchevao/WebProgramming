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

@WebServlet(name="student-enrollment-web-servlet",urlPatterns = "/servlet/StudentEnrollmentSummary")

public class StudentEnrollmentSummary extends HttpServlet {

    private final StudentService studentService;
    private final SpringTemplateEngine springTemplateEngine;

    public StudentEnrollmentSummary(StudentService studentService, SpringTemplateEngine springTemplateEngine) {
        this.springTemplateEngine=springTemplateEngine;
        this.studentService=studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req,resp,req.getServletContext());
        context.setVariable("list", studentService.listAll());
        this.springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selected = (String) req.getParameter("list");
        req.getSession().setAttribute("list", selected);
        req.getSession().setAttribute("student", studentService.listAll());
        resp.sendRedirect("/servlet/StudentEnrollmentSummary");
    }
}
