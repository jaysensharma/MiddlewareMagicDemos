import java.io.*;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BindJndiDemo
{
public final static String JNDI_FACTORY="org.jboss.naming.remote.client.InitialContextFactory";

public static void main(String[] args) throws Exception 
  {
     if (args.length != 1) {
         System.out.println("Usage: java BindJndiDemo URL");
         System.out.println("Example:  java BindJndiDemo remote://localhost:4447");
         return;
     } 

     InitialContext ic = getInitialContext(args[0]);
     BindJndiDemo demo = new BindJndiDemo();

     System.out.println("\n\n\t *** Following shows Looking up a Primitive Datatype located in the JNDI ***");
     Object primitiveLookup=ic.lookup("test");
     System.out.println("\tic.lookup(\"test\") primitiveLookup = "+primitiveLookup);


     System.out.println("\n\n\t *** Following shows Looking up a Custom Bean/Object located in the JNDI ***");
     test.jndi.demo.TestBean testBean=(test.jndi.demo.TestBean)ic.lookup("test2");
     System.out.println("\t(test.jndi.demo.TestBean)ic.lookup(\"test2\") testBean = "+testBean);
     System.out.println("\tname="+testBean.getName()+"\tvalue="+testBean.getValue());

     System.out.println("\n\n\t *** Following shows the JNDI Name Aliasing ***");
     Object obj=ic.lookup("test3");
     System.out.println("\tAliasing Demo ic.lookup(\"test3\") = "+obj);
     obj=ic.lookup("test4");
     System.out.println("\tAliasing Demo ic.lookup(\"test4\") = "+obj);
     obj=ic.lookup("test5");
     System.out.println("\tAliasing Demo ic.lookup(\"test5\") = "+obj);
  }


private static InitialContext getInitialContext(String url) throws NamingException
     {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
        env.put(Context.PROVIDER_URL, url);

        //*************** UserName & Password for the Initial Context for JNDI lookup *************************
        env.put(Context.SECURITY_PRINCIPAL, "testuser");
        env.put(Context.SECURITY_CREDENTIALS, "testpassword");
        InitialContext ic=new InitialContext(env);
        System.out.println("\n\n\t Got InitialContext ic: "+ic);
        return ic;
     }
  }
