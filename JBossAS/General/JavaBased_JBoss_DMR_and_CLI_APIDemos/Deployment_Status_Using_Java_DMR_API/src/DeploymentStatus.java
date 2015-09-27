import java.net.UnknownHostException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.sasl.RealmCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.net.InetAddress;
import java.io.IOException;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.as.controller.client.ModelControllerClient.Factory;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.Property;

public class DeploymentStatus
  {     
    static String host = "localhost"; 
    //static String host = "10.165.11.54";         // Make sure your are running your Domain Cntroller on IP Address as   ./domain.sh -bmanagement 10.165.11.54
    static String password = "admin123";             // ManagementRealm user credentials
    static String userid = "admin";  
    static String serverName = "JayServer";        // Server on which the application is deployed
    static String hostControllerName="hostA";      // host.xml file contains the host name <host name="master" ....>
    static String applicationName="HelloEJB.jar";  // Application which we want to monitor

    public static void main (String[] args) 
      {
          ModelControllerClient client = null;
          try 
            {
                client = createClient (InetAddress.getByName(host), 9999, userid, password.toCharArray(), "ManagementRealm" );
            }
          catch ( UnknownHostException uhe) 
            {
               System.out.println("UHE: " + uhe.getMessage());
            }

          try {
                  ModelNode op = new ModelNode(); 
                  op.get("operation").set("read-attribute"); 
                  op.get("name").set("status");

                  ModelNode address = op.get("address"); 
                  address.add("host", hostControllerName); 
                  address.add("server", serverName);
                  address.add("deployment", applicationName); 
                  op.get("operations").set(true);

                  System.out.println("\n\n\t client = "+client); 
                  ModelNode appStatus = client.execute(op); 

                  String status = appStatus.get("result").toString();

                  if(!status.equals("undefined"))
                    {
                       System.out.println("\n\n\tApplication "+applicationName+" Status : "+ status);
                    }
                  else
                    {
                       System.out.println("\n\n\tSeems that Application "+applicationName+" is NOT deployed. Status :  "+status);                       
                    }
        
                  if (client != null ) client.close();
               }
           catch (Exception e) {
                  System.out.println ("Exception: " + e.getMessage());
                  e.printStackTrace();
               }
        }

    
      static ModelControllerClient createClient (final InetAddress host, final int port,  final String username, final char[] password, final String securityRealmName) 
       {  
         final CallbackHandler callbackHandler = new CallbackHandler() 
          { 
             public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException 
               {
                  for (Callback current : callbacks) 
                   { 
                      if (current instanceof NameCallback) 
                        { 
                           NameCallback ncb = (NameCallback) current; 
                           ncb.setName(username);
                        } 
                      else if (current instanceof PasswordCallback) 
                        { 
                            PasswordCallback pcb = (PasswordCallback) current; 
                            pcb.setPassword(password); 
                        }
                      else if (current instanceof RealmCallback) 
                         { 
                            RealmCallback rcb = (RealmCallback) current; 
                            rcb.setText(rcb.getDefaultText()); 
                         } 
                      else { 
                            throw new UnsupportedCallbackException(current); 
                         } 
                     } 
                  } 
                }; 
            return ModelControllerClient.Factory.create(host, port, callbackHandler); 
          }
   }
