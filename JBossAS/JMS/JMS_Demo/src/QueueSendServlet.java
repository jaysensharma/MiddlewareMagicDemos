import java.io.*;
import javax.jms.*;
import javax.naming.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;

public class QueueSendServlet extends HttpServlet
{
	static PrintWriter out;
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
 	 {
		try
		{
			out=response.getWriter();
			InitialContext ic = getInitialContext();
			init(ic, QUEUE_NAME);
			sendMsg(request.getParameter("jmsMessage"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//*************** Connection Factory JNDI name *************************
	public final static String CNN_FACTORY="/ConnectionFactory";

	//*************** Connection Factory JNDI name *************************
	public final static String QUEUE_NAME="java:jboss/exported/TestQueueOne";

	private QueueConnectionFactory qconFactory;
	private QueueConnection qcon;
	private QueueSession qsession;
	private QueueSender qsender;
	private Queue queue;
	private TextMessage msg;

	public void init(Context ctx, String queueName)throws NamingException, JMSException
	{
		qconFactory = (QueueConnectionFactory) ctx.lookup(CNN_FACTORY);
		qcon = qconFactory.createQueueConnection();
		qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) ctx.lookup(queueName);
		qsender = qsession.createSender(queue);
		msg = qsession.createTextMessage();
		qcon.start();
	}

	private  void sendMsg(String userMessage) throws IOException, JMSException
	{
		boolean readFlag=true;
		out.println("");
		out.println("");
		out.println("");
		out.println("<h1>Queue Sender Servlet</h1>");
		out.println("Following Messages has been sent !!!");
		out.println("<BR>====================================<BR>");
                        msg.setText(userMessage);
                        qsender.send(msg);
   			out.println("Message Sent => "+userMessage);
		out.println("<BR>====================================");
		out.println("");
		out.println("");
		out.println("");
                qcon.close();
	}

	private static InitialContext getInitialContext() throws NamingException
	{
		return new InitialContext();
	}
}
