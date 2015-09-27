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
import java.io.IOException;
import org.jboss.dmr.ModelNode;
import org.jboss.dmr.Property;


public class NativeManagementClient
{     
    //static String host = "10.10.10.96"; // "localhost";
    static String host = "localhost";
    static int port = 9999;
    static String password = "admin";
    static String userid = "admin123";  
    static String hostControllerName="master";
    static String serverName="JayServer";
    static boolean standaloneMode=true;     //  If you are running your Servers in Domain Mode then set this to false. 
    static ModelControllerClient client = null;    
    
    public static void main (String[] args) 
       {
           try {
                 createAndCheckClientConnection();
                 performOperations(client);
               }

           catch (java.io.IOException ex) 
               {
                 System.out.println("\n\n Unable to Connect to the Host: "+host+" or Port:"+port+"\t\t" + ex.getMessage());
                 //ex.printStackTrace();
               }
           catch (Exception ex) 
               {
                 System.out.println("\n\nSomeThing is Wrong: "+ex.getMessage());
                 //ex.printStackTrace();
               }

           try {        
                 if (client != null ) client.close();
               }
           catch (Exception e) 
               {
                 System.out.println ("Exception: " + e.getMessage());
                 e.printStackTrace();
               }
        }

    
    public static void createAndCheckClientConnection() throws IOException
     {
                 /* NOTE: For hostname as "localhost" there is no need to pass the username & password  */
                 //client = ModelControllerClient.Factory.create(host, port, null); 
               
                 /* If you are running this program remotely then you need to pass the credentials */
                 client = createClient (InetAddress.getByName(host), port, userid, password.toCharArray(), "ManagementRealm" );
                 System.out.println("Got the client: "+client);

                 /* Checking whether Client is actually connected to Controller or not by executing dummy ModelNode? */
                 ModelNode op = new ModelNode(); 
                 op.get("operation").set("read-resource"); 
                 ModelNode returnVal=client.execute(op);
                 System.out.println("\n\nrelease-version: " + returnVal.get("result").get("release-version").asString());
                 System.out.println("release-codename: " + returnVal.get("result").get("release-codename").asString());
     }

   public static void performOperations(ModelControllerClient client)
      {
                  if(standaloneMode==true)
                     {
                        // FOR STANDALONE MODE use the "TestStandaloneModel" class as following:
                        TestStandaloneModel testStandaloneModel=new TestStandaloneModel();               

                        /* Getting Web Subsystem runtime Details */
                        testStandaloneModel.getWebSubsystemRuntimeDetails(client);
                        /* Testing Non XA DataSource ExampleDS */
                        testStandaloneModel.testNonXADataSource(client,"ExampleDS");
                        /* Monitoring Application Statistics where application name is "Log4jDemo.war" */
                        testStandaloneModel.monitorApplicationStatistics(client,"Log4jDemo.war");
                     }
                  if(standaloneMode==false)
                    {
                        TestDomainModeModel testDomainModeModel= new TestDomainModeModel();
                        /* Getting Web Subsystem runtime Details */
                        testDomainModeModel.getWebSubsystemRuntimeDetails(client,hostControllerName,serverName);
                        /* Testing Non XA DataSource ExampleDS */
                        testDomainModeModel.testNonXADataSource(client,hostControllerName,serverName,"ExampleDS");
                        /* Monitoring Application Statistics where application name is "Log4jDemo.war" */
                        testDomainModeModel.monitorApplicationStatistics(client,hostControllerName,serverName,"Log4jDemo.war");                   
                    }
      }

     static ModelControllerClient createClient (final InetAddress host, final int port, final String username, final char[] password, final String securityRealmName) 
      { 
         final CallbackHandler callbackHandler = new CallbackHandler() { 
                 public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException 
                   {
                       for (Callback current : callbacks) { 
                          if (current instanceof NameCallback) { 
                             NameCallback ncb = (NameCallback) current; 
                             System.out.println("\n\t\tncb.setName() = "+new String(password));
                             ncb.setName(new String(password)); 
                            } else if (current instanceof PasswordCallback) { 
                             PasswordCallback pcb = (PasswordCallback) current; 
                             System.out.println("\n\t\tpcb.setPassword() = "+username);
                             pcb.setPassword(username.toCharArray()); 
                            } else if (current instanceof RealmCallback) { 
                             RealmCallback rcb = (RealmCallback) current; 
                             System.out.println("\n\t\trcb.getDefaulttest() = "+rcb.getDefaultText());
                             rcb.setText(securityRealmName); 
                            } else { 
                             throw new UnsupportedCallbackException(current); 
                        } 
                    }  
               } 
             };  
          return ModelControllerClient.Factory.create(host, port, callbackHandler); 
       }
}
