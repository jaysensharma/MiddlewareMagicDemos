package ejb3;
import javax.ejb.*;
@Stateless
public class HelloBean implements HelloRemote {
        public String sayHello(String name) {
           System.out.println("\n\t hello("+name+") invoked, Hello HelloBean Date:  "+new java.util.Date());
           return "Hello, "+name+", Date: "+new java.util.Date();
        }
 }
