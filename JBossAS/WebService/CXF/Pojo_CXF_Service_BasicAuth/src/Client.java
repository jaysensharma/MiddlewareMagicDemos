package client;
import java.net.URL;
import  javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
public class Client
  {
       public static void main(String ar[]) throws Exception
       {
          String BASIC_USER="userone";
          String BASIC_PWD="passwordone";
          //String SERVICE_ENDPOINT="http://localhost:8080/Pojo_CXF_Service_BasicAuthDemo/PojoCXFService";
          String SERVICE_ENDPOINT=ar[0];
          URL wsdl_URL=new URL(SERVICE_ENDPOINT+"?wsdl");
          QName SERVICE = new QName("http://ws/", "PojoCXFServiceService");

          JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
          factory.setServiceClass(PojoCXFService.class);
          factory.setAddress(SERVICE_ENDPOINT);
          PojoCXFService service = (PojoCXFService) factory.create();

          System.out.println("Got the Service: "+service);

          BindingProvider bp = (BindingProvider)service;
          bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, BASIC_USER);
          bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, BASIC_PWD);
          System.out.println(service.sayHello("MiddlewareMagic"));
       }
  }
