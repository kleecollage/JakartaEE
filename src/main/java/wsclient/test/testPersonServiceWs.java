package wsclient.test;

import wsclient.service.Person;
import wsclient.service.PersonServiceImplService;
import wsclient.service.PersonServiceWs;

import java.util.List;

public class testPersonServiceWs {
    public static void main(String[] args) {
        PersonServiceWs personService = new PersonServiceImplService().getPersonServiceImplPort();

        System.out.println("Executing service list persons ws");
        List<Person> persons = personService.listPersons();
        for (Person person : persons) {
            System.out.println("person ID = " + person.getIdPerson() + ", name: " + person.getName() +
                    ", surname: " + person.getSurname() + ", email: " + person.getEmail());
        }
        System.out.println("End service list persons ws");
    }
}
