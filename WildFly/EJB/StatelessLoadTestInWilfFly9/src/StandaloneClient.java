package client;
import ejb3.AccountRemote;
import javax.naming.*;
import java.util.*;
public class StandaloneClient {
	public static void main(String args[]) {
                Context context = null;
		try {
                        Properties props = new Properties();
                        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
                        props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080"); 
                        props.put(Context.SECURITY_PRINCIPAL, "ejbUserOne");
                        props.put(Context.SECURITY_CREDENTIALS, "ejbPasswordOne@123");
                        props.put("jboss.naming.client.ejb.context", true);
                        context = new InitialContext(props);	
	                System.out.println("\n\tGot initial Context: "+context);	

                        for(int i=0;i<100;i++) {
                           MyThread thread = new MyThread(context);
                           thread.setName("TestThread-"+i);
                           thread.start();
                        }

                } catch(Exception e) {
			e.printStackTrace();
		}
	}
 }

class MyThread extends Thread {

   Context ctx=null;
   MyThread (Context ctx) {
       this.ctx=ctx;
   }

   public void run() {
      try {
             AccountRemote remote = (AccountRemote) ctx.lookup("Stateless_EJB_Ear/Stateless_ejb/AccountBean!ejb3.AccountRemote");
	     String result = remote.helloWorld("Hello - " + Thread.currentThread().getName());
	     System.out.println(remote + "\tResult : " + result);
       } catch(Exception e) {
             e.printStackTrace();
       }
   }
}
