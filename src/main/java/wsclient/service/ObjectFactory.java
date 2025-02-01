
package wsclient.service;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the wsclient.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListPersons_QNAME = new QName("http://service.jta.gm/", "listPersons");
    private final static QName _ListPersonsResponse_QNAME = new QName("http://service.jta.gm/", "listPersonsResponse");
    private final static QName _Person_QNAME = new QName("http://service.jta.gm/", "person");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: wsclient.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListPersons }
     * 
     */
    public ListPersons createListPersons() {
        return new ListPersons();
    }

    /**
     * Create an instance of {@link ListPersonsResponse }
     * 
     */
    public ListPersonsResponse createListPersonsResponse() {
        return new ListPersonsResponse();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListPersons }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListPersons }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jta.gm/", name = "listPersons")
    public JAXBElement<ListPersons> createListPersons(ListPersons value) {
        return new JAXBElement<ListPersons>(_ListPersons_QNAME, ListPersons.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListPersonsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListPersonsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jta.gm/", name = "listPersonsResponse")
    public JAXBElement<ListPersonsResponse> createListPersonsResponse(ListPersonsResponse value) {
        return new JAXBElement<ListPersonsResponse>(_ListPersonsResponse_QNAME, ListPersonsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Person }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Person }{@code >}
     */
    @XmlElementDecl(namespace = "http://service.jta.gm/", name = "person")
    public JAXBElement<Person> createPerson(Person value) {
        return new JAXBElement<Person>(_Person_QNAME, Person.class, null, value);
    }

}
