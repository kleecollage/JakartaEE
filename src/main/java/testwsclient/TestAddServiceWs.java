package testwsclient;


import jakarta.xml.ws.Service;
import wsclient.service.AddServiceImplService;
import wsclient.service.AddServiceWs;
import javax.xml.namespace.QName;
import java.net.URL;

public class TestAddServiceWs {
    public static void main(String[] args) {
        AddServiceWs addServiceWs = new     AddServiceImplService().getAddServiceImplPort();
        System.out.println("Executing add service WS");
        int x = 6;
        int y = 3;
         int result = addServiceWs.sum(x, y);
         System.out.println("result = " + result);
        System.out.println("End add service WS");
    }
}
