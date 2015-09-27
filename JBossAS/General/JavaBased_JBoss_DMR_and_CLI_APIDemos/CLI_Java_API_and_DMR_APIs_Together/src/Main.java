import java.io.IOException;

// CLI API
import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.CommandContextFactory;
import org.jboss.as.cli.CommandLineException;
import org.jboss.as.cli.CommandFormatException;

// DMR API
import org.jboss.dmr.ModelNode;

// Controller Client API
import org.jboss.as.controller.client.ModelControllerClient;

public class Main
 {
   public static void main(String[] args) throws CommandLineException, IOException
   {
      String host = args[0];;
      int port = Integer.parseInt(args[1]);
      String appPath=args[2];

      Main main=new Main();
      CommandContext ctx = CommandContextFactory.getInstance().newCommandContext("admin","admin123".toCharArray());
      ctx.connectController(host, port);
      ModelNode deployRequest = null; 
      //ModelNode undeployRequest = null; 
      try {
                deployRequest = ctx.buildRequest("deploy "+appPath);
                //undeployRequest = ctx.buildRequest("undeploy myapp.war");
      } catch (CommandFormatException e) {
                e.printStackTrace();
      } 
      main.deployApp(ctx,deployRequest);      
      ctx.disconnectController();
   }

   public void deployApp(CommandContext ctx,ModelNode modelNode) {   
         System.out.println("Trying to deploy application.");
         ModelControllerClient client = ctx.getModelControllerClient();
         if(client != null) {
            try {
                client.execute(modelNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
         } else {
            // the client is not available, meaning the connection to the controller has not been established, which means ctx.connectController(...) or ctx.handle("connect") haven't been executed before
              System.out.println("the client (ModelControllerClient) is not available. Connection error");
        }
    }

   public void undeployApp(CommandContext ctx,ModelNode modelNode) {   
         System.out.println("Trying to Undeploy application.");
         ModelControllerClient client = ctx.getModelControllerClient();
         if(client != null) {
            try {
                client.execute(modelNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
         } else {
              System.out.println("the client (ModelControllerClient) is not available. Connection error");
        }
    }
 }
