import java.net.UnknownHostException;
import java.net.InetAddress;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.sasl.RealmCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.as.controller.client.ModelControllerClient.Factory;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.Property;

public class JBossClient
{     
    static String host = "localhost"; 
    //static String host = "10.10.10.10";
    static String password = "admin123";
    static String userid = "admin";  

    public static void main (String[] args) {
           ModelControllerClient client = null;        
           try {
                //client = ModelControllerClient.Factory.create(InetAddress.getByName("localhost"), 9999);
                client = createClient (InetAddress.getByName(host), 9999, userid, password.toCharArray(), "ManagementRealm" );
           }
           catch ( UnknownHostException uhe) {
                System.out.println("UHE: " + uhe.getMessage());
           }

           try {
                ModelNode op = new ModelNode(); 
                op.get("operation").set("read-resource"); 
                ModelNode address = op.get("address"); 
                address.add("subsystem", "web"); 
                address.add("connector", "http"); 
                op.get("include-runtime").set(true); 
                op.get("include-defaults").set(true);
 
                ModelNode returnVal = client.execute(op); 
                System.out.println(returnVal.get("result").toString());
                if (client != null ) client.close();
            }
          catch (Exception e) {
                System.out.println ("Exception: " + e.getMessage());
            }
      }

    
    static ModelControllerClient createClient (final InetAddress host, final int port,  final String username, final char[] password, final String securityRealmName) { 
          final CallbackHandler callbackHandler = new CallbackHandler() { 
                                 public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {     
                                                      for (Callback current : callbacks) { 
                                                                if (current instanceof NameCallback) { 
                                                                     NameCallback ncb = (NameCallback) current; 
                                                                     ncb.setName(username); 
                                                                } else if (current instanceof PasswordCallback) { 
                                                                     PasswordCallback pcb = (PasswordCallback) current; 
                                                                     //pcb.setPassword("admin123".toCharArray()); 
                                                                     pcb.setPassword(password); 
                                                                } else if (current instanceof RealmCallback) { 
                                                                     RealmCallback rcb = (RealmCallback) current; 
                                                                     rcb.setText(rcb.getDefaultText()); 
                                                                } else { 
                                                                     throw new UnsupportedCallbackException(current); 
                                                                } 
                                                        } 
                                                     } 
                                                 }; 
                                       return ModelControllerClient.Factory.create(host, port, callbackHandler); 
                                     }
     }
