package client;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import ejb.service.CommonService;

public class Client {
      public static void main(String ar[]) throws Exception {
          Context context=null;
          try {
                Properties props = new Properties();
                props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
                props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");   // NOTICE: "http-remoting" and port "8080"
                props.put(Context.SECURITY_PRINCIPAL, "ejbuser");
                props.put(Context.SECURITY_CREDENTIALS, "ejbuser@1");
                props.put("jboss.naming.client.ejb.context", true);
                context = new InitialContext(props);	
	            System.out.println("\n\tGot initial Context: "+context);		
           } catch (Exception e) {
                e.printStackTrace();
           }

            // Lookup Format will be 
            // <app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified-classname-of-the-remote-interface>
   
          CommonService remote=(CommonService)context.lookup("testEAR/testEJB/CommonServiceBean!ejb.service.CommonService");
          System.out.println("\n\t remote.helloWorld(\"RedHat!!!\") = "+remote.helloWorld("MiddlewareMagic!!!"));
          // Notice our interceptor will change the Parameter "MiddlewareMagic!!!" to ""MiddlewareMagic!!!"XYZ"
       }
  }
