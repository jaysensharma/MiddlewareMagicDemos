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


public class UnDeployApplicationUsingDMR
  {     
    //static String host = "localhost"; 
    static String host = "localhost";      // Make sure your are running your Domain Cntroller on IP Address as   ./domain.sh -bmanagement 10.165.11.54
    static String password = "admin";         // ManagementRealm user credentials
    static String userid = "admin123";  
    static String APP_ARCHIVE_NAME="passed Via command Line ....check main()";      //  see build.xml file last few lines
    static ModelControllerClient client = null;

    public static void main (String[] args) 
      {
          APP_ARCHIVE_NAME=args[0];
          
          try 
            {
                client = createClient (InetAddress.getByName(host), 9999, userid, password.toCharArray(), "ManagementRealm" );
                UnDeployApplicationUsingDMR  ref=new UnDeployApplicationUsingDMR();
                ref.undeployApp();
                if (client != null ) client.close();
            }
          catch ( UnknownHostException uhe) 
            {
               System.out.println("UHE: " + uhe.getMessage());
            }
          catch ( IOException ie) 
            {
               System.out.println("IOEx: " + ie.getMessage());
            }
          finally{
                    try{  if (client != null ) client.close(); }
                    catch(Exception e){  e.printStackTrace();  }
            }

        }

      public void undeployApp()
        {
          try {
                    DefaultOperationRequestBuilder builder = new DefaultOperationRequestBuilder();
                    builder.setOperationName("undeploy");
                    builder.addNode("deployment", APP_ARCHIVE_NAME);

                    DefaultOperationRequestBuilder builder2 = new DefaultOperationRequestBuilder();
                    builder2.setOperationName("remove");
                    builder2.addNode("deployment", APP_ARCHIVE_NAME);

                    ModelNode[] steps = new ModelNode[2];
                    steps[0] = builder.buildRequest();
                    steps[1] = builder2.buildRequest();

                    ModelNode compositeOp = new ModelNode();
                    compositeOp.get("operation").set("composite");
                    for(ModelNode step : steps) {
                          compositeOp.get("steps").add(step);
                    }
        
                   OperationBuilder ob = new OperationBuilder(compositeOp);
                   ModelNode result  = client.execute(ob.build());

                   String status = result.get("result").toString();
                   System.out.println("\n\nApplication "+APP_ARCHIVE_NAME+" undeployment result :"+status);
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
                           ncb.setName(new String(password));
                        } 
                      else if (current instanceof PasswordCallback) 
                        { 
                            PasswordCallback pcb = (PasswordCallback) current; 
                            pcb.setPassword(username.toCharArray()); 
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
