package demo;
import javax.jms.JMSException;  
import javax.jms.Message;  
import javax.jms.MessageListener;  
import javax.jms.TextMessage;  
  
public class ExampleListener implements MessageListener {  
    public void onMessage(Message message) {  
        try {  
            String msg = ((TextMessage) message).getText();  
            System.out.println("MESSAGE TEXT: " + msg);  
        } catch (JMSException e) {  
            throw new RuntimeException(e);  
        }  
    }  
}  
