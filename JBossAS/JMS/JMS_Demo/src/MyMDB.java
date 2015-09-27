import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.ejb.MessageDriven;
import javax.ejb.ActivationConfigProperty;

@MessageDriven(activationConfig =
        {
        @ActivationConfigProperty(propertyName="destinationType", propertyValue="javax.jms.Queue"),
        @ActivationConfigProperty(propertyName="destination", propertyValue="java:jboss/exported/TestQueueOne")
        })

public class MyMDB implements MessageListener
{
    public void onMessage(Message message)
    {
        TextMessage textMessage = (TextMessage) message;
        try
       {
            System.out.println("===> MyMDB Received: "+ textMessage.getText());
        }
        catch (JMSException e)
        {
            e.printStackTrace();
        }
    }
}
