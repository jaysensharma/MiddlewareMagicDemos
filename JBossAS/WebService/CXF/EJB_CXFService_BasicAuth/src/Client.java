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
          //String SERVICE_ENDPOINT="http://localhost:8080/EJB_CXF_BasicAuthDemo/SecureCXF_EJB";
          String SERVICE_ENDPOINT=ar[0];
          URL wsdl_URL=new URL(SERVICE_ENDPOINT+"?wsdl");
          QName SERVICE = new QName("http://ws/", "SecureCXF_EJBService");

          JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
          factory.setServiceClass(SecureCXFEJB.class);
          factory.setAddress(SERVICE_ENDPOINT);
          SecureCXFEJB service = (SecureCXFEJB) factory.create();
          System.out.println("Got the Service: "+service);
          BindingProvider bp = (BindingProvider)service;
          bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, BASIC_USER);
          bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, BASIC_PWD);
          System.out.println(service.sayHello("MiddlewareMagic"));

        /* // Below is the alternative Client Code which can be used to invole the service //

          SecureCXFEJBService service=new SecureCXFEJBService(wsdl_URL,SERVICE);
          SecureCXFEJB port=service.getSecureCXFEJBPort();
          System.out.println("Got the Service port: "+port);
          BindingProvider bp = (BindingProvider)port;
          bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, BASIC_USER);
          bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, BASIC_PWD);
          System.out.println(port.sayHello("MiddlewareMagic"));
        */
       }
  }
