package gm.jta.web;

import gm.jta.domain.Person;
import gm.jta.service.PersonService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.apache.logging.log4j.*;
import org.primefaces.event.RowEditEvent;
import java.util.List;

@Named("personBean")
@RequestScoped
public class PersonBean {

    private static Logger log = LogManager.getRootLogger();

    @Inject
    PersonService personService;

    private Person personSelected;

    List<Person> persons;

    public PersonBean() {
        log.debug("Init object PersonBean");
    }

    @PostConstruct
    public void init() {
        // INIT VARIABLES
        this.persons = personService.listPersons();
        log.debug("People recover on ManagedBean:" + this.persons);
        this.personSelected = new Person();
    }

    public void editListener(RowEditEvent event) {
        Person person = (Person) event.getObject();
        personService.updatePerson(person);
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Person getPersonSelected() {
        return personSelected;
    }

    public void setPersonSelected(Person personSelected) {
        this.personSelected = personSelected;
    }

    public void addPerson() {
        this.personService.registerPerson(this.personSelected);
        this.persons.add(personSelected);
        this.personSelected = null;
    }

    public void removePerson() {
        this.personService.deletePerson(this.personSelected);
        this.persons.remove(personSelected);
        this.personSelected = null;
    }

    public void resetPersonSelected() {
        this.personSelected = new Person();
    }
}

















