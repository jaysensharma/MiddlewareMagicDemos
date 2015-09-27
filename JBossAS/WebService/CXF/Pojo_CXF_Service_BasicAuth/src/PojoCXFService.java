package ws;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;


import org.jboss.security.annotation.SecurityDomain;
@SecurityDomain("cxfservice-security-domain")   



@WebService(name = "PojoCXFService", targetNamespace="http://ws/")
@RolesAllowed("TestRole")
public class PojoCXFService
{
    public String sayHello(String name)
     {
        System.out.println("\n\t [PojoCXFService] sayHello() invoked : Hello, Mr. "+name);
        return "Hello, Mr. "+name;
     }
}

