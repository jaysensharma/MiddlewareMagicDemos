package client;
import java.net.URL;
import javax.xml.namespace.QName;

/* Client side Logging related CXF APIs*/
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

public class Test_CXF_Client
  {
     public static void main(String ar[]) throws Exception
      {
        String WSDL_URL=ar[0]+"?wsdl";
        DemoCXFService service=new DemoCXFService(new URL(WSDL_URL));
        DemoCXF port=service.getDemoCXFPort();

        /* Following part of code is needed for client side Logging of Soap request/response */
        /* We need to make sure that we place the "log4j.properties" file inside clients classpath */
        Client client = ClientProxy.getClient(port);
        client.getInInterceptors().add(new LoggingInInterceptor());
        client.getOutInterceptors().add(new LoggingOutInterceptor());


        Employee emp=new Employee();
        emp.setEmpNo(1000L);
        emp.setEmpName("MiddlewaremagicEmployee");
        emp.setEmpSalary(6000L);

        System.out.println("\n\nBefore  EmpNo: "+emp.getEmpNo()+",  Name:"+emp.getEmpName()+",  Sal:"+emp.getEmpSalary());
        emp=port.processEmployeeSalary(emp,1000L);
        System.out.println("\n\nAfter   EmpNo: "+emp.getEmpNo()+",  Name:"+emp.getEmpName()+",  Sal:"+emp.getEmpSalary());
      }
  }
