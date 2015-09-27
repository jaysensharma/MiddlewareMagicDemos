package test.jndi.demo;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;
public class MyCustomObjectFactory implements ObjectFactory 
   {
       public MyCustomObjectFactory() 
       {
            System.out.println("[MyCustomObjectFactory] MyCustomObjectFactory initialized.");
       }

       public Object getObjectInstance(Object obj, Name name, Context nameCtx,Hashtable environment) throws Exception 
       {
            TestBean bean = new TestBean("City","Pune (India)");
            return bean;
       }
   }
