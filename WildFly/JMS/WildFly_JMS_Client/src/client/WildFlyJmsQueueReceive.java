package client;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

//jms stuff
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;

public class WildFlyJmsQueueReceive  implements MessageListener {
  public final static String JMS_CONNECTION_FACTORY_JNDI="jms/RemoteConnectionFactory";
  public final static String JMS_QUEUE_JNDI="jms/queue/TestQ";
  public final static String JMS_USERNAME="jmsuser";       //  The role for this user is "guest" in ApplicationRealm
  public final static String JMS_PASSWORD="jmsuser@123";  
  public final static String WILDFLY_REMOTING_URL="http-remoting://localhost:8080";


  private QueueConnectionFactory qconFactory;
  private QueueConnection qcon;
  private QueueSession qsession;
  private QueueReceiver qReceiver;
  private Queue queue;
  private TextMessage msg;
  private boolean quit = false;

  public static void main(String[] args) throws Exception {
    InitialContext ic = getInitialContext();
    WildFlyJmsQueueReceive wildflyJmsQueueReceive = new WildFlyJmsQueueReceive();
    wildflyJmsQueueReceive.init(ic, JMS_QUEUE_JNDI);
    System.out.println("JMS Ready To Receive Messages (To quit, send a \"quit\" message from QueueSender.class).");
    // Waiting until a "quit" message has been received.
    synchronized(wildflyJmsQueueReceive) {
         while (! wildflyJmsQueueReceive.quit) {
             try {
                   wildflyJmsQueueReceive.wait();
             }
             catch (InterruptedException ie) {
                   ie.printStackTrace();
             }
         }
     }
     wildflyJmsQueueReceive.close();
  }
  
  public void init(Context ctx, String queueName) throws NamingException, JMSException {
    qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_CONNECTION_FACTORY_JNDI);

    //  If you won't pass jms credential here then you will get 
    // [javax.jms.JMSSecurityException: HQ119031: Unable to validate user: null]    
    qcon = qconFactory.createQueueConnection(this.JMS_USERNAME, this.JMS_PASSWORD);   
    
    qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    queue = (Queue) ctx.lookup(queueName);
    qReceiver = qsession.createReceiver(queue);
    qReceiver.setMessageListener(this);
    qcon.start();
  }

  public void onMessage(Message msg) {
     try {
           String msgText;
           if (msg instanceof TextMessage) {
              msgText = ((TextMessage)msg).getText();
           } else {
              msgText = msg.toString();
           }
           System.out.println("\n<Msg_Receiver> "+ msgText );
           if (msgText.equalsIgnoreCase("quit")) {
             synchronized(this) {
             	 quit = true;
             	 this.notifyAll(); // Notify main thread to quit
             }
           }
      } catch (JMSException jmse) {
          jmse.printStackTrace();
     }
  }

  public void close() throws JMSException {
    qReceiver.close();
    qsession.close();
    qcon.close();
  }
  
  private static InitialContext getInitialContext() throws NamingException {
     InitialContext context=null;
     try {
           Properties props = new Properties();
           props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
           props.put(Context.PROVIDER_URL, WILDFLY_REMOTING_URL);   // NOTICE: "http-remoting" and port "8080"
           props.put(Context.SECURITY_PRINCIPAL, JMS_USERNAME);
           props.put(Context.SECURITY_CREDENTIALS, JMS_PASSWORD);
           props.put("jboss.naming.client.ejb.context", true);
           context = new InitialContext(props);	
	       System.out.println("\n\tGot initial Context: "+context);		
      } catch (Exception e) {
           e.printStackTrace();
      }
    return context;
  }
}