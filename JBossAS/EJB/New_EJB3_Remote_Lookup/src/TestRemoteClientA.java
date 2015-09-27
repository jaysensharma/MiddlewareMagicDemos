package client;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import remote.CallerRemote;
public class TestRemoteClientA
  {
      public static void main(String ar[]) throws Exception 
       {
          Context context=null;
       try
         {
                Properties props = new Properties();
                props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
                props.put(Context.PROVIDER_URL, "remote://localhost:4447"); 
                props.put(Context.SECURITY_PRINCIPAL, "testuser");
                props.put(Context.SECURITY_CREDENTIALS, "testpassword");
                context = new InitialContext(props);	
	        System.out.println("\n\tGot initial Context: "+context);		
         }
       catch (Exception e)
         {
                e.printStackTrace();
          }

         // Lookup Format will be 
         // <app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified-classname-of-the-remote-interface>
   
         CallerRemote remote=(CallerRemote)context.lookup("TestRemoteEJBEAR/remoteEJB//CallerBean!remote.CallerRemote");
         System.out.println("\n\t remote.testMethod(\"MiddlewareMagic\") = "+remote.testMethod("MiddlewareMagic"));
       }
  }
