package gm.jpa.web;

import gm.jpa.domain.User;
import gm.jpa.service.UserService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    @Inject // not works for WildFly
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = this.userService.findAllUsers();
        System.out.println("users = " + users);
        request.setAttribute("users", users);
        request.getRequestDispatcher("/usersList.jsp").forward(request, response);
    }
}
