package gm.jta.service;

import gm.jta.domain.Person;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface PersonServiceWs {
    @WebMethod
    public List<Person> listPersons();
}
