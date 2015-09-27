package demo;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.BytesMessage;
import javax.jms.Session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;

public class SpringQueueSender {
 
  public static void main(String[] args) {
    SpringQueueSender test=new SpringQueueSender();
    ApplicationContext appContext = new ClassPathXmlApplicationContext("producerApplicationContext.xml");
    System.out.println("\n\n\t[SpringQueueSender] main called and appContext : "+appContext);
    JmsTemplate jmsTemplate=(JmsTemplate) appContext.getBean("jmsTemplate");   
    jmsTemplate.send(new MessageCreator() {
                int index=0;
                String text="Text-Generated At=> "+new java.util.Date();
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage(text);
                    message.setIntProperty("messageCount", index++);
                    System.out.println("Sending message: " + text);
                    return message;
                }
            });
    System.exit(0);
   }
 }
