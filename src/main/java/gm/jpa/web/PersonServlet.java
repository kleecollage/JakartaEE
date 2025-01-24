package gm.jpa.web;

import gm.jpa.domain.Person;
import gm.jpa.service.PersonService;
import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PersonServlet", urlPatterns = "/persons")
public class PersonServlet extends HttpServlet {
    @Inject // not works for WildFly
    PersonService personService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Person> persons = this.personService.listPersons();
        System.out.println("persons = " + persons);
        request.setAttribute("persons", persons);
        request.getRequestDispatcher("/personsList.jsp").forward(request, response);
    }
}
