package client;
import javax.naming.*;
import java.util.*;
import ejb3.HelloRemote;

public class WebLogicEJBClient {
  public static void main(String ar[]) throws Exception {
    Context context = null;
	try {
          Properties props = new Properties();
          props.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
          props.put(Context.PROVIDER_URL, "t3://localhost:7001"); 
          //props.put(Context.SECURITY_PRINCIPAL, "ejbUserOne");
          //props.put(Context.SECURITY_CREDENTIALS, "ejbPasswordOne@123");
          context = new InitialContext(props);	
          
          System.out.println("\n\n****************************************");
	      System.out.println("\n\t[WebLogicEJBClient] Got initial Context: "+context);	

          String EJB_JNDI_NAME="java:global/TestApp/TestEJB/HelloBean!ejb3.HelloRemote";
          
          HelloRemote remote = (HelloRemote) context.lookup(EJB_JNDI_NAME);
          String result = remote.sayHello("MiddlewareMagic!!!");
          System.out.println("\n\t[WebLogicEJBClient] Got response from EJB : " + result);
          System.out.println("\n****************************************\n\n");
          
     } catch(Exception e) {
			e.printStackTrace();
     }     
   }
}
