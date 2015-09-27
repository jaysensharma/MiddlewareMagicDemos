package client;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;

import org.jboss.ejb.client.ContextSelector;
import org.jboss.ejb.client.EJBClientConfiguration;
import org.jboss.ejb.client.EJBClientContext;
import org.jboss.ejb.client.PropertiesBasedEJBClientConfiguration;
import org.jboss.ejb.client.remoting.ConfigBasedEJBClientContextSelector;
import remote.CallerRemote;
public class TestRemoteClientA
  {
	private static String HOST_A = "localhost";
	private static String HOST_B = "localhost";
	private static String Username = "ejbone";
	private static String Password = "password";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties p = new Properties();
		p.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED","false");
		p.put("remote.connections", "one,two");
		p.put("remote.connection.one.port", "4547");
		p.put("remote.connection.one.host", HOST_A);
		p.put("remote.connection.one.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS","false");
		p.put("remote.connection.one.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT","false");
		p.put("remote.connection.one.username", Username);
		p.put("remote.connection.one.password", Password);

		p.put("remote.connection.two.port", "4647");
		p.put("remote.connection.two.host", HOST_B);
		p.put("remote.connection.two.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS","false");
		p.put("remote.connection.two.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT","false");
		p.put("remote.connection.two.username", Username);
		p.put("remote.connection.two.password", Password);

		p.put("remote.clusters", "ejb");
		p.put("remote.cluster.ejb.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", "false");
		p.put("remote.cluster.ejb.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
		p.put("remote.cluster.ejb.username", Username);
		p.put("remote.cluster.ejb.password", Password);


  	        EJBClientConfiguration cc = new PropertiesBasedEJBClientConfiguration(p);
		ContextSelector<EJBClientContext> selector = new ConfigBasedEJBClientContextSelector(cc);
		EJBClientContext.setSelector(selector);


                Properties props = new Properties();
                props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
                InitialContext ctx = null;
		try {
                        ctx = new InitialContext(props);
			CallerRemote remote = null;

			remote = (CallerRemote) ctx.lookup("ejb:/remoteEJB/CallerBean!remote.CallerRemote");
			for(int i=0; i<100; i++){
				System.out.println("\n\t remote.testMethod(i) = "+ remote.testMethod("Test-"+i));
					Thread.sleep(500);
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
  }
