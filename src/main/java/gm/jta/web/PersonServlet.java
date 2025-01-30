package gm.jta.web;

import gm.jta.domain.Person;
import gm.jta.service.PersonService;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PersonServlet", urlPatterns = "/persons")
public class PersonServlet extends HttpServlet {
    @Inject
    PersonService personService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Person> persons = this.personService.listPersons();
        System.out.println("persons = " + persons);
        request.setAttribute("persons", persons);
        // request.getRequestDispatcher("/listPeople.xhtml").forward(request, response);
    }
}
