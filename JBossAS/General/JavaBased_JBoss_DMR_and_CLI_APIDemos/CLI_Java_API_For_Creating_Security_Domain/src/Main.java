import java.io.IOException;
import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.CommandContextFactory;
import org.jboss.as.cli.CommandLineException;

public class Main
{
   public static void main(String[] args) throws CommandLineException, IOException
   {
      String host = "localhost";
      int port = 9999;
      Main main=new Main();
      CommandContext ctx = CommandContextFactory.getInstance().newCommandContext("admin","admin123".toCharArray());
      ctx.connectController(host, port);
      main.createSecurityDomain(ctx);      
      ctx.disconnectController();
   }

       public void createSecurityDomain(CommandContext ctx)
       {   
         System.out.println("Setting up the security-domain with CLI");
         try {
                ctx.connectController();
                ctx.handle("/subsystem=security/security-domain=test-security-domain/:add(cache-type=default)");
                String command="/subsystem=security/security-domain=test-security-domain/authentication=classic:add(login-modules=[{\"code\"=>\"my.custom.LoginModule\", \"flag\"=>\"required\", \"module\"=>\"my.custom.module\", \"module-options\"=>[(\"optionA\"=>\"option_A_Value\"),(\"optionB\"=>\"option_B_Value\"),(\"unauthenticatedIdentity\"=>\"nobody\")] }]   )";
                ctx.handle(command);
             } 
         catch (CommandLineException e) 
             {
                e.printStackTrace();
             } 
         finally 
             {
                ctx.terminateSession();
             }          
        }
}
