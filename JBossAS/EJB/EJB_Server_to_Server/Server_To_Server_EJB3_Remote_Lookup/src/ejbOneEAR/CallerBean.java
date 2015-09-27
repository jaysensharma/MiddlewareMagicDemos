package ejb.one;
import javax.ejb.*;
import javax.naming.*;
import java.util.*;
import ejb.two.TestRemote;
@Stateless  
@Remote(CallerRemote.class)
public class CallerBean implements CallerRemote
 {
     public String testMethod(String name)
	    {
                   String result="";
		   System.out.println("\n\n\t[CallerBean] Bean's testMethod(String name) called....");
                   System.out.println("\n\n\t[CallerBean] Is Now calling the TestBean deployed on another JBoss Instance.");
                   try {
                         Hashtable<String,String> props = new Hashtable<String,String>();
                         props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
                         
                         /******* FOLLOWING  CODE IS NOT NEEDED  ********/
                         //props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
                         //props.put(Context.PROVIDER_URL, "remote://localhost:4647"); 
                         //props.put(Context.SECURITY_PRINCIPAL, "ejbUserOne");
                         //props.put(Context.SECURITY_CREDENTIALS, "ejbPasswordOne");
                         
                         Context context = new javax.naming.InitialContext(props);
                         String jndiName="ejb:" + "ejbTwoEAR/remoteEjbTwo/TestBean!ejb.two.TestRemote";
                         TestRemote remote = (TestRemote) context.lookup(jndiName);

                         result = remote.helloWorld("TestMessage For TestBean: MiddlewareMagic");
                         System.out.println("[CallerBean] Received result from TestBean: " + result);
                        } 
                    catch (Exception e) 
                        {
                         throw new RuntimeException(e);
                        }
		   return "[CallerBean] returned Hello "+name+"\tTestBean: " + result;
	    }
 }
