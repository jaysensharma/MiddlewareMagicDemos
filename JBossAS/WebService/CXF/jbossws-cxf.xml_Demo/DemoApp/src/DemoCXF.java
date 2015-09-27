package ws;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import java.util.Collection;

/* For Server side Logging */
//import org.apache.cxf.feature.Features;

@WebService(name = "DemoCXF", serviceName ="DemoCXFService", portName ="DemoCXFPort", targetNamespace="http://test.org")
//@Features(features = "org.apache.cxf.feature.LoggingFeature") 

public class DemoCXF
{
  @Resource
  WebServiceContext ctx;

  @WebMethod()
  public String sayHello(String name)
  {
    System.out.println("\n\n\t Method Invoked....String sayHello("+name+")");
    return "Hello JBossAS7 User: "+name;
  }

  @WebMethod()
  public String getProperty(String propertyName)
  {
    System.out.println("\n\n\t Method Invoked....String getProperty(String propertyName)");
    return "RETURNED: "+(Collection) ctx.getMessageContext().values();
  }
}
