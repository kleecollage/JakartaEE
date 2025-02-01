package gm.jax.ws.sum.beans;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface AddServiceWs {
    @WebMethod
    public int sum(int a, int b);
}
