package web.programming.aud.wpaud.web.servlet;

import org.springframework.stereotype.Repository;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;
import web.programming.aud.wpaud.model.exceptions.InvalidUserCredentialsException;
import web.programming.aud.wpaud.service.impl.AuthServiceImpl;
import web.programming.aud.wpaud.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "loginservlet", urlPatterns = "/servlet/login")
public class LoginServlet extends HttpServlet {

    private final AuthServiceImpl authService;
    private final SpringTemplateEngine springTemplateEngine;

    public LoginServlet(AuthServiceImpl authService, SpringTemplateEngine springTemplateEngine) {

        this.authService = authService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        springTemplateEngine.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getParameter("username");
        String password = (String) req.getParameter("password");

        User user = null;
        try {
            user = authService.login(username, password);
        } catch (InvalidUserCredentialsException exception) {
            WebContext webContext = new WebContext(req, resp, req.getServletContext());
            webContext.setVariable("hasError", true);
            webContext.setVariable("error", exception.getMessage());
            springTemplateEngine.process("login.html", webContext, resp.getWriter());
        }

        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/servlet/thymeleaf/category");
    }
}
