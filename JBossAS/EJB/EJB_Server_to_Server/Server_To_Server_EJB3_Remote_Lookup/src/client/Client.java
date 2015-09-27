package client;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import ejb.one.CallerRemote;
public class Client
  {
      public static void main(String ar[]) throws Exception 
       {
          Context context=null;
       try
         {
                Properties props = new Properties();
                props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
                props.put(Context.PROVIDER_URL, "remote://localhost:4547"); 
                props.put(Context.SECURITY_PRINCIPAL, "ejbUserOne");
                props.put(Context.SECURITY_CREDENTIALS, "ejbPasswordOne");
                //props.put("jboss.naming.client.ejb.context", true);
                context = new InitialContext(props);	
	        System.out.println("\n\tGot initial Context: "+context);		
         }
       catch (Exception e)
         {
                e.printStackTrace();
          }

         // Lookup Format will be 
         // <app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified-classname-of-the-remote-interface>
   
         CallerRemote remote=(CallerRemote)context.lookup("ejbOneEAR/remoteEjbOne/CallerBean!ejb.one.CallerRemote");
         System.out.println("\n\t remote.testMethod(\"MiddlewareMagic\") = "+remote.testMethod("MiddlewareMagic"));
       }
  }
