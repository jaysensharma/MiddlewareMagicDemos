<%@ page import="javax.management.*,javax.management.remote.*,test.startup.*" %>


<%
     MBeanServer mbeanServer = java.lang.management.ManagementFactory.getPlatformMBeanServer(); 
     System.out.println("Got the MBeanServer.... "+mbeanServer);

     ObjectName objectName=new ObjectName("test.startup:service=HelloWorld");

     HelloWorldServiceMBean bean=new HelloWorldService();
     mbeanServer.registerMBean(bean, objectName); 
     System.out.println("MBean Registered with ObjectName:  "+objectName);
%>

<h1> Check the JBossAS7 Console to see if Your MXBean  is registered properly or not.   You can also use the "$JBOSS_HOME/bin/jconsole.sh" script as well to start the JConsole and to see if the MXBean is accessible through it or not </h1>
