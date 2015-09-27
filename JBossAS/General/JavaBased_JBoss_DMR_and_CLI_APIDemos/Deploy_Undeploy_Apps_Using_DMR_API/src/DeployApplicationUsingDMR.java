import java.net.UnknownHostException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.sasl.RealmCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.as.controller.client.ModelControllerClient.Factory;
import java.net.InetAddress;
import java.io.*;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.Property;
import org.jboss.as.cli.operation.impl.DefaultOperationRequestBuilder;
import org.jboss.as.controller.client.OperationBuilder;


public class DeployApplicationUsingDMR
  {     
    //static String host = "localhost"; 
    static String host = "localhost";      // Make sure your are running your Domain Cntroller on IP Address as   ./domain.sh -bmanagement 10.165.11.54
    static String password = "admin123";         // ManagementRealm user credentials
    static String userid = "admin";  
    static String APP_PATH="Passed Via Command Line ... check main()";   // see build.xml file last few lines
    static String APP_ARCHIVE_NAME="passed Via command Line ....check main()";      //  see build.xml file last few lines

    public static void main (String[] args) 
      {
          APP_PATH=args[0];
          APP_ARCHIVE_NAME=args[1];
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
		    File deployment = new File(APP_PATH + "/" + APP_ARCHIVE_NAME);
                    ModelNode addDeploymentOp = new ModelNode();
                    addDeploymentOp.get("operation").set("add"); 
                    ModelNode address = addDeploymentOp.get("address"); 
                    address.add("deployment",APP_ARCHIVE_NAME);
                    addDeploymentOp.get("content").get(0).get("input-stream-index").set(0);
        
                    DefaultOperationRequestBuilder builder = new DefaultOperationRequestBuilder();
                    builder.setOperationName("deploy");
                    builder.addNode("deployment", APP_ARCHIVE_NAME);

                    ModelNode[] steps = new ModelNode[2];
                    steps[0] = addDeploymentOp;
                    steps[1] = builder.buildRequest();

                    ModelNode compositeOp = new ModelNode();
                    compositeOp.get("operation").set("composite");
                    for(ModelNode step : steps) {
                          compositeOp.get("steps").add(step);
                    }
        
                   OperationBuilder ob = new OperationBuilder(compositeOp);
                   ob.addInputStream(new FileInputStream(deployment));
        
                   ModelNode result  = client.execute(ob.build());
                   String status = result.get("result").toString();
                   System.out.println("\n\n\t Application "+APP_ARCHIVE_NAME+" deployment result :"+status);
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
                           ncb.setName(new String(username));
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
