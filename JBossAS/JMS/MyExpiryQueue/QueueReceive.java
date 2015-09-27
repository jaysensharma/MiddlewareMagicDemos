import java.util.Hashtable;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueReceive implements MessageListener
{
public final static String JNDI_FACTORY="org.jboss.naming.remote.client.InitialContextFactory";

//*************** Connection Factory JNDI name *************************
public final static String JMS_FACTORY="/jms/RemoteConnectionFactory";

//*************** Remote enabled Queue JNDI name *************************
public final static String QUEUE="/MyQueue";

private QueueConnectionFactory qconFactory;
private QueueConnection qcon;
private QueueSession qsession;
private QueueReceiver qreceiver;
private Queue queue;
private boolean quit = false;

public void onMessage(Message msg)
{
	try
	{
		String msgText;
		if (msg instanceof TextMessage)
		{
			msgText = ((TextMessage)msg).getText();
		}
		else
		{
			msgText = msg.toString();
		}

		System.out.println("\n\t<Msg_Receiver> "+ msgText );

		if (msgText.equalsIgnoreCase("quit"))
		{
			synchronized(this)
			{
				quit = true;
				this.notifyAll(); // Notify main thread to quit
			}
		}
	}
	catch (JMSException jmse)
	{
		jmse.printStackTrace();
	}
}

public void init(Context ctx, String queueName) throws NamingException, JMSException
{
	try
	{
		qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);

		//*************** Creating Queue Connection using the UserName & Password *************************
		qcon = qconFactory.createQueueConnection("testuser","testpassword");   			//<------------- Change the UserName & Password

		qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) ctx.lookup(queueName);

		//Sleeping for 5's so that the message gets expired.
		System.out.println("Sleeping for 5's till the message expire...");
		Thread.sleep(5000);

		qreceiver = qsession.createReceiver(queue);
		qreceiver.setMessageListener(this);
		qcon.start();
	}
	catch (InterruptedException jmse)
	{
		jmse.printStackTrace();
	}

}

public void close()throws JMSException
{
qreceiver.close();
qsession.close();
qcon.close();
}

public static void main(String[] args) throws Exception
{
if (args.length != 1)
{
System.out.println("Usage: java QueueReceive URL");
return;
}
InitialContext ic = getInitialContext(args[0]);
QueueReceive qr = new QueueReceive();
qr.init(ic, QUEUE);
System.out.println("JMS Ready To Receive Messages (To quit, send a \"quit\" message from QueueSender.class).");

synchronized(qr)
{
while (! qr.quit)
{
try
{
qr.wait();
}
catch (InterruptedException ie)
{}
}
}
qr.close();
}

private static InitialContext getInitialContext(String url) throws NamingException
{
Hashtable env = new Hashtable();
env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
env.put(Context.PROVIDER_URL, url);

//*************** UserName & Password for the Initial Context for JNDI lookup *************************
env.put(Context.SECURITY_PRINCIPAL, "testuser");
env.put(Context.SECURITY_CREDENTIALS, "testpassword");

return new InitialContext(env);
}
}

