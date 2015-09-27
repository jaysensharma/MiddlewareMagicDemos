import java.io.*;
import java.io.*;
import java.util.Hashtable;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueueSend
{
public final static String JNDI_FACTORY="org.jboss.naming.remote.client.InitialContextFactory";

//*************** Connection Factory JNDI name *************************
public final static String JMS_FACTORY="/jms/RemoteConnectionFactory";

//*************** Remote enabled Queue JNDI name *************************
public final static String QUEUE="/MyQueue";

private QueueConnectionFactory qconFactory;
private QueueConnection qcon;
private QueueSession qsession;
private QueueSender qsender;
private Queue queue;
private TextMessage msg;

public void init(Context ctx, String queueName)throws NamingException, JMSException
{
qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);

//*************** Creating Queue Connection using the UserName & Password *************************
qcon = qconFactory.createQueueConnection("testuser","testpassword");   			//<------------- Change the UserName & Password

qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
queue = (Queue) ctx.lookup(queueName);
qsender = qsession.createSender(queue);

//Messages sent by this sender will be live for 1s (1000ms) before expiration
qsender.setTimeToLive(1000);

msg = qsession.createTextMessage();
qcon.start();
}

public void send(String message) throws JMSException {
msg.setText(message);
qsender.send(msg);
System.out.println("Message="+message+ " Time to live ="+qsender.getTimeToLive());
}

public void close() throws JMSException {
qsender.close();
qsession.close();
qcon.close();
}

public static void main(String[] args) throws Exception {
if (args.length != 1) {
System.out.println("Usage: java QueueSend URL");
return;
}
InitialContext ic = getInitialContext(args[0]);
QueueSend qs = new QueueSend();
qs.init(ic, QUEUE);
readAndSend(qs);
qs.close();
}

private static void readAndSend(QueueSend qs) throws IOException, JMSException
{
String line="Test Message Body with counter = ";
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
boolean readFlag=true;
System.out.println("\n\tStart Sending Messages (Enter QUIT to Stop):\n");
while(readFlag)
{
System.out.print("<Msg_Sender> ");
String msg=br.readLine();
if(msg.equals("QUIT") || msg.equals("quit"))
{
qs.send(msg);
System.exit(0);
}
qs.send(msg);
System.out.println();
}
br.close();
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

