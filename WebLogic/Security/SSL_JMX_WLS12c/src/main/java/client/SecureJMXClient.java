package client;
import weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean;
import weblogic.management.runtime.ServerRuntimeMBean;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;
import weblogic.management.jmx.MBeanServerInvocationHandler;
import java.util.Hashtable;
import java.io.IOException;
import java.net.MalformedURLException;
import weblogic.management.runtime.JDBCDataSourceRuntimeMBean;
import javax.management.*;
import javax.naming.*;
 
public class SecureJMXClient {
 
   private static MBeanServerConnection connection;
   private static JMXConnector connector;
   private static final ObjectName service;
   private static String combea = "com.bea:Name=";
   private static String service1 = "DomainRuntimeService,Type=weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean";
   private static String service2 = "RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean";
 
   static {
        try {
               service =new ObjectName(combea + service1);
        } catch (MalformedObjectNameException e) {
               throw new AssertionError(e.getMessage());
        }
   }
 
   public static void initConnection(String hostname, String portString, String username, String password) throws IOException,MalformedURLException {
    String protocol = "t3s";
    Integer portInteger = Integer.valueOf(portString);
    int port = portInteger.intValue();
    String jndiroot = "/jndi/";
    String mserver = "weblogic.management.mbeanservers.domainruntime";
    JMXServiceURL serviceURL = new JMXServiceURL(protocol, hostname, port, jndiroot + mserver);
    Hashtable h = new Hashtable();
    h.put(Context.SECURITY_PRINCIPAL, username);
    h.put(Context.SECURITY_CREDENTIALS, password);
    h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,"weblogic.management.remote");
    connector = JMXConnectorFactory.connect(serviceURL, h);
    connection = connector.getMBeanServerConnection();
   }
 
   public static ObjectName[] getServerRuntimes() throws Exception {
        return (ObjectName[]) connection.getAttribute(service,"ServerRuntimes");
    }
 
   public void printNameAndState() throws Exception {
    ObjectName arr[]=getServerRuntimes();       
    for(ObjectName temp : arr)
        System.out.println("Servers: "+temp);
 
    ObjectName domain = (ObjectName) connection.getAttribute(service,"DomainConfiguration");
    System.out.println("Domain: " + domain.toString());
    ObjectName[] servers = (ObjectName[]) connection.getAttribute(domain,"Servers");
    for (ObjectName server : servers) {
        String serverState="UNKNOWN";
        String aName = (String) connection.getAttribute(server,"Name");
        try {
            ObjectName ser= new ObjectName("com.bea:Name="+aName+",Location="+aName+",Type=ServerRuntime");
            serverState=(String) connection.getAttribute(ser,"State");
            System.out.println("Server: "+aName+"t State: "+serverState);
         } catch(Exception e) {
            System.out.println("Server: "+aName+"t State: SHUTDOWN (or) In State : "+ serverState);
         }
      }
    }
 
  public static void main(String[] args) throws Exception {
    String hostname   = "localhost";    // CHANGE ME !!!
    String portString = "7443";         // CHANGE ME !!!
    String username   = "weblogic";     // CHANGE ME !!!
    String password   = "weblogic1";    // CHANGE ME !!!
    
    SecureJMXClient s = new SecureJMXClient();
    initConnection(hostname, portString, username, password);
    s.printNameAndState();
    connector.close();
   }
 }