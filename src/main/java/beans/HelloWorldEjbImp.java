package beans;

import jakarta.ejb.Stateless;

@Stateless
public class HelloWorldEjbImp implements HelloWorldEjbRemote {

    @Override
    public int sum(int a, int b) {
        System.out.println("Executing sum method");
        return a + b;
    }
}
