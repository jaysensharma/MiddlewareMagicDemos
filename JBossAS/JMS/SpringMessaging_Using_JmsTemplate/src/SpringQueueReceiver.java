package demo;
import javax.jms.*;
import javax.jms.Message;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.transaction.annotation.Transactional;

public class SpringQueueReceiver{
 public static void main(String[] args) throws  Exception {
    SpringQueueReceiver test=new SpringQueueReceiver();
    ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("consumerApplicationContext.xml");
    System.out.println("\n\n\t [SpringQueueReceiver] main called and appContext : "+appContext);
    System.out.println("Check if Listener is running or not ? The Spring Context will be closed within nextr 60 seconds.");
    Thread.sleep(60000);
    appContext.close();
    System.out.println("\n\nSpring Context is closed now.");    
  }
}
