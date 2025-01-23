package test;

import beans.HelloWorldEjbRemote;

import javax.naming.*;
import java.util.Properties;

public class TestHelloWorldEjb {
    public static void main(String[] args) {
        System.out.println("INIT CALL TO EJB FROM THE CLIENT\n");
        try {
            // JNDI CONFIG FOR WILDFLY
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

            Context jndi = new InitialContext(props);
            // HelloWorldEjbRemote helloWorldEjb = (HelloWorldEjbRemote) jndi.lookup("java:global/ejb/HelloWorldEjbImp!beans.HelloWorldEjbRemote");
            HelloWorldEjbRemote helloWorldEjb = (HelloWorldEjbRemote) jndi.lookup("ejb:/ejb/HelloWorldEjbImp!beans.HelloWorldEjbRemote");

            int result = helloWorldEjb.sum(5, 15);
            System.out.println("result EJB 5 + 15 = " + result);
        } catch (NamingException e) {
            e.printStackTrace(System.out);
        }
    }
}
