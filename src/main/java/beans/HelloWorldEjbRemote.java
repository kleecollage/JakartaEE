package beans;

import jakarta.ejb.Remote;

@Remote
public interface HelloWorldEjbRemote {
    int sum(int a, int b);
}
