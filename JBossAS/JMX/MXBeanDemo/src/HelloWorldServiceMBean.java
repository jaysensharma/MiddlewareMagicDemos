package test.startup;
import javax.management.*;

@MXBean
public interface HelloWorldServiceMBean
{
   public String getMessage();
   public void setMessage(String message);
   
   public void printMessage();
}
