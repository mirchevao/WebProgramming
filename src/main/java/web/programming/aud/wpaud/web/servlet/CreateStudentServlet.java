package web.programming.aud.wpaud.web.servlet;

import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import web.programming.aud.wpaud.model.Student;
import web.programming.aud.wpaud.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="create-student-web-servlet",urlPatterns = "/servlet/CreateStudent")
public class CreateStudentServlet extends HttpServlet {

    private final StudentService studentService;
    private final SpringTemplateEngine springTemplateEngine;


    public CreateStudentServlet(StudentService studentService, SpringTemplateEngine springTemplateEngine) {
        this.studentService = studentService;
        this.springTemplateEngine = springTemplateEngine;

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("list", studentService.listAll());
        this.springTemplateEngine.process("listStudents.html",context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentName = (String)req.getParameter("name");
        String studentSurname = (String)req.getParameter("surname");
        String studentUsername = (String)req.getParameter("username");
        String studentPassword = (String)req.getParameter("password");

        studentService.save(studentUsername,studentPassword,studentName,studentSurname);
        resp.sendRedirect("/servlet/AddStudent");
    }
}
