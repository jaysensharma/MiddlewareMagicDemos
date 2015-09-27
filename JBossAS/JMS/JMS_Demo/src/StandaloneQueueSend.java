import java.io.*;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.*;


public class StandaloneQueueSend
{
public final static String JNDI_FACTORY="org.jboss.naming.remote.client.InitialContextFactory";

public static void main(String[] args) throws Exception
  {
     if (args.length != 1) {
         System.out.println("Usage: java StandaloneQueueSend URL");
         System.out.println("Example:  java StandaloneQueueSend remote://localhost:4447");
         return;
     } 

     InitialContext ic = getInitialContext(args[0]);
     StandaloneQueueSend demo = new StandaloneQueueSend();

     System.out.println("\n\n\t *** Following shows Looking up a Primitive Datatype located in the JNDI ***");
    // Object testQueueOne=ic.lookup("TestQueueOne");
    // System.out.println("\tic.lookup(\"TestQueueOne\") testQueueOne = "+testQueueOne);
     demo.init(ic,"TestQueueOne");
  }

private static InitialContext getInitialContext(String url) throws NamingException
     {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, url);

        //*************** UserName & Password for the Initial Context for JNDI lookup *************************
        env.put(Context.SECURITY_PRINCIPAL, "TestJMSUser");
        env.put(Context.SECURITY_CREDENTIALS, "password");
        InitialContext ic=new InitialContext(env);
        System.out.println("\n\n\t Got InitialContext ic: "+ic);
        return ic;
     }

	private QueueConnectionFactory qconFactory;
	private QueueConnection qcon;
	private QueueSession qsession;
	private QueueSender qsender;
	private Queue queue;
	private TextMessage msg;

	public void init(Context ctx, String queueName)throws NamingException, JMSException
	{
		qconFactory = (QueueConnectionFactory) ctx.lookup("/jms/RemoteConnectionFactory");
		qcon = qconFactory.createQueueConnection("TestJMSUser","password");
		qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) ctx.lookup(queueName);
		qsender = qsession.createSender(queue);
		msg = qsession.createTextMessage();
		qcon.start();

                for(int i=1;i<=5;i++)
                  {
                    String message="Hello Message - "+i+" at "+ new java.util.Date();
                    msg.setText(message);
                    System.out.println("\n\t Message Sent : "+message); 
                    qsender.send(msg);
                  }
               qcon.close();                
	}

  }
