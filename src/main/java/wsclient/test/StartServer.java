package wsclient.test;

import jakarta.jws.WebService;
import jakarta.xml.ws.Endpoint;
import wsclient.service.Person;
import wsclient.service.PersonServiceWs;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import javax.xml.namespace.QName;
import java.util.List;


public class StartServer {
    private static final String ADDRESS = "http://localhost:8080/jta/PersonServiceImpl";

//    public static void main(String[] args) {
//        Endpoint endpoint = Endpoint.publish(ADDRESS, new PersonServiceImpl());
//        System.out.println("PersonServiceImpl SOAP endpoint started at " + ADDRESS + "?wsdl");
//        System.out.println("Press Ctrl+C to stop.");
//        Runtime.getRuntime().addShutdownHook(new Thread(endpoint::stop));
//        try {
//            Thread.currentThread().join();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//    }

    public static void main(String[] args) throws Exception {
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
        factory.setServiceClass(PersonServiceWs.class);
        factory.setAddress(ADDRESS);
        factory.setServiceBean(new PersonServiceImpl());
        factory.setServiceName(new QName("http://service.jta.gm/", "PersonServiceImplService"));
        factory.setEndpointName(new QName("http://service.jta.gm/", "PersonServiceImplPort"));
        factory.create();

        System.out.println("Servidor CXF iniciado");
        System.out.println("WSDL: http://localhost:8080/jta/PersonServiceImpl?wsdl");
        Thread.sleep(Long.MAX_VALUE);
    }

    @WebService(
            serviceName = "PersonServiceImplService",
            portName = "PersonServiceImplPort",
            targetNamespace = "http://service.jta.gm/",
            endpointInterface = "wsclient.service.PersonServiceWs"
    )

    public static class PersonServiceImpl implements PersonServiceWs {
        @Override
        public List<Person> listPersons() {
            Person p1 = new Person();
            p1.setIdPerson(1);
            p1.setName("Ada");
            p1.setSurname("Lovelace");
            p1.setEmail("ada@example.com");
            p1.setPhone("555-0101");

            Person p2 = new Person();
            p2.setIdPerson(2);
            p2.setName("Alan");
            p2.setSurname("Turing");
            p2.setEmail("alan@example.com");
            p2.setPhone("555-0102");

            return List.of(p1, p2);
        }
    }
}

