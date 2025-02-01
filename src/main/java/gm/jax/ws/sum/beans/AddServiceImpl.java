package gm.jax.ws.sum.beans;

import jakarta.ejb.Stateless;
import jakarta.jws.WebService;

@Stateless
@WebService(endpointInterface = "gm.jax.ws.sum.beans.AddServiceWs")
public class AddServiceImpl implements AddServiceWs{
    @Override
    public int sum(int a, int b) {
        return a + b;
    }
}
