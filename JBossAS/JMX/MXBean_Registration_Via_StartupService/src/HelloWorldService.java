package test.startup;

public class HelloWorldService implements HelloWorldServiceMXBean
{
   private String message = "Sorry no message today";


   public HelloWorldService()
   {
       System.out.println("\n\tHelloWorldService() Initialized");
   }

   public String getMessage()
   {
      System.out.println("\n\tgetMessage() : "+message);
      return message;
   }

   public void setMessage(String message)
   {
      System.out.println("\n\tsetMessage() : "+message);
      this.message = message;
   }

   public void printMessage()
   {
      System.out.println("\n\tprintMessage() : "+message);
   }

}

