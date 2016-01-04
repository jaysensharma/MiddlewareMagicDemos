package client;
import ejb3.MathRemote;
import javax.naming.*;
import java.util.*;

public class StandaloneClient {
	public static void main(String args[]) {
          Context context=null;
          String JNDI_NAME="ejb:/EJB_WildFly_Https/MathBean!ejb3.MathRemote"; 
             
          // We are passing the following properties via the maven-exec-plugin
          //System.setProperty("javax.net.ssl.trustStorePassword","middleware+magic+client");
          //System.setProperty("javax.net.ssl.trustStore","/PATH/TO/clientTrustStore.keystore");
          
          try { 
                Properties props = new Properties();
                props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");    
                context = new InitialContext(props);	
                
	            System.out.println("\n\tGot initial Context: "+context);		
           } catch (Exception e) {
                e.printStackTrace();
           }

           try {
		        MathRemote remote=(MathRemote)context.lookup(JNDI_NAME);
	   	        int sum=remote.add(2,10);	
	   	        String result = remote.sayHello("MiddlewareMagic");	
		        System.out.println("\n\t remote.add(2,10) => "+ sum);
		        System.out.println("\n\t remote.sayHello('MiddlewareMagic') => "+ result);		        
    	   } catch(Exception e) {
		       e.printStackTrace();
	       }
	}
}
