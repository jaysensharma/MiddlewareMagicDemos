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
                props.put(Context.SECURITY_PRINCIPAL, "ejbUser");
                props.put(Context.SECURITY_CREDENTIALS, "ejbPassword");

                //props.put("jboss.naming.client.remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED","true");
                //props.put("jboss.naming.client.connect.options.org.xnio.Options.SSL_STARTTLS","true");

                props.put("jboss.naming.client.ejb.context", true);
                props.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
                context = new InitialContext(props);	
	        System.out.println("\n\t--------------------------\nGot initial Context: "+context);		
         }
       catch (Exception e)
         {
                e.printStackTrace();
          }

         // Lookup Format will be 
         // <app-name>/<module-name>/<distinct-name>/<bean-name>!<fully-qualified-classname-of-the-remote-interface>
   
         CallerRemote remote=(CallerRemote)context.lookup("TestRemoteEJBEAR/remoteEJB//CallerBean!remote.CallerRemote");
         //System.out.println("\n\t remote.testMethod(\"Common-MiddlewareMagic\") = "+remote.commonMethod("Common-MiddlewareMagic"));

         System.out.println("\n\t remote.testMethod(\"MiddlewareMagic\") = "+remote.testMethod("MiddlewareMagic"));
       }
  }
