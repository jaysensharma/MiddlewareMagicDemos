import java.io.IOException;
import org.jboss.as.cli.CommandContext;
import org.jboss.as.cli.CommandContextFactory;
import org.jboss.as.cli.CommandLineException;

public class Main
{
  public static void main(String[] args) throws CommandLineException, IOException
  {
    String host = "localhost";   // 10.10.10.10
    int port = 9999;
    String username="admin";
    char[] password="admin123".toCharArray();
    CommandContext ctx=null;
    try 
     {
      ctx = CommandContextFactory.getInstance().newCommandContext(username,password);
      ctx.connectController(host, port);
      String cliCommand="/subsystem=web:read-resource(include-runtime=true)";
      ctx.handle(cliCommand);    
      ctx.disconnectController();
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
