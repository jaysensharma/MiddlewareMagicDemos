package client;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import ejb3.CallerRemote;
public class Client {
     public static void main(String ar[]) throws Exception {
          Context context=null;
          try {
                Properties props = new Properties();
                props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
                context = new InitialContext(props);	
	            System.out.println("\n\tGot initial Context: "+context);		
          } catch (Exception e) {
                e.printStackTrace();
          }

          //   When the invocation starts you try stopping the WildFly and then restart it back to find out if client reconnection works or not.
          CallerRemote remote=(CallerRemote)context.lookup("ejb:TestEAR/remoteEjbOne/CallerBean!ejb3.CallerRemote");
          for (int i = 0; i <= 300; i++) {  
             try {
                   System.out.println("\n\t remote.testMethod(\"MiddlewareMagic!!!\") = "+remote.testMethod("MiddlewareMagic!!!"));
                   Thread.sleep(500);
              } catch(Exception e) {                    
                    System.out.println("\n\tEXCEPTION: " + e.getMessage());
                    e.printStackTrace();
                    try{ Thread.sleep(500); } catch(InterruptedException ee) {  ee.printStackTrace(); } 
              }  
          }  
     }
  }
