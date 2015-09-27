package test;
public class Message
  {
    public static final int HELLO   = 0;
    public static final int GOODBYE = 1;

    private String          message;
    private int             status; 

    public void setMessage(String message)
     {
         this.message=message;
         System.out.println("[Message] 'setMessage' method called with message : "+message);
     }
    public void setStatus(int status)
     {
         this.status=status;
         System.out.println("[Message] 'setStatus' method called with status : "+status);
     }
    public int getStatus()
     {
         System.out.println("[Message] 'getStatus' method called retuning status : "+status);
         return status;
     }
    public String getMessage()
     {
         System.out.println("[Message] 'getMessage' method called retuning message : "+message);
         return message;
     }
  }
