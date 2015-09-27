import java.net.UnknownHostException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.sasl.RealmCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.util.*;
import java.net.InetAddress;
import java.io.IOException;

import org.jboss.dmr.ModelNode;
import org.jboss.dmr.Property;
import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.as.controller.client.ModelControllerClient.Factory;

public class CreateSecurityDomain
  {     
    //static String host = "10.10.10.10"; 
    static String host = "localhost";
    static String password = "admin123";
    static String userid = "admin";  
    static ModelControllerClient client = null;

    private static final String COMPOSITE = "composite";
    private static final String OP = "operation";
    private static final String OP_ADDR = "address";
    private static final String STEPS = "steps";

    public static void main (String[] args) throws Exception
      {
          try 
            {
                //client = ModelControllerClient.Factory.create(InetAddress.getByName("localhost"), 9999);
                client = createClient (InetAddress.getByName(host), 9999, userid, password.toCharArray(), "ManagementRealm" );
            }
          catch ( UnknownHostException uhe) 
            {
               System.out.println("UHE: " + uhe.getMessage());
            }

          try {
                     CreateSecurityDomain CreateSecurityDomain=new CreateSecurityDomain();
                     ModelNode test=CreateSecurityDomain.createAddSecurityDomain("test-security-domain");
                     ModelNode returnValA = client.execute(test); 
                     String resultA=returnValA.get("outcome").toString();
                     System.out.println("\n\t login-module Creation Was = "+resultA);
                     System.out.println("\n\nModelNode = "+test.toString());
                     System.out.println("\n\nRecieved Response: -"+ returnValA.toString());
                     if (client != null ) client.close();
              }
           catch (Exception e)
              {
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


    public static ModelNode createAddSecurityDomain(final String name) {
        ModelNode addDomain = new ModelNode();
        addDomain.get(OP).set("add");
        addDomain.get(OP_ADDR).add("subsystem", "security").add("security-domain", name);
        addDomain.get("cache-type").set("default");

        ModelNode addDomainContent = new ModelNode();
        addDomainContent.get(OP).set("add");
        addDomainContent.get(OP_ADDR).add("subsystem", "security").add("security-domain", name).add("authentication", "classic");
        ModelNode loginModules = addDomainContent.get("login-modules");

        ModelNode remotingModule = new ModelNode();
        remotingModule.get("code").set("UsersRoles");
        remotingModule.get("flag").set("required");
        ModelNode moduleOptions = remotingModule.get("module-options");
        moduleOptions.get("usersProperties").set("${jboss.server.config.dir}/test-users.properties");
        moduleOptions.get("rolesProperties").set("${jboss.server.config.dir}/test-roles.properties");
        moduleOptions.get("unauthenticatedIdentity").set("nobody");
        loginModules.add(remotingModule);

        return createCompositeOp(Arrays.asList(new ModelNode[] { addDomain, addDomainContent }));
    }

    private static ModelNode createCompositeOp(final Collection<ModelNode> operations) {
        ModelNode composite = new ModelNode();
        composite.get(OP).set(COMPOSITE);
        composite.get(OP_ADDR).setEmptyList();

        ModelNode steps = composite.get(STEPS);
        for (ModelNode current : operations) {
            steps.add(current);
        }
        return composite;
    }
  }
