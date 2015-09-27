package sar.dependency.alternative;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//commons logging
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.management.*;
import javax.management.remote.*;
import test.startup.*;

@Startup
@Singleton  
public class SingletonServiceBean
 {
     private static Log logger = LogFactory.getLog(SingletonServiceBean.class);
     public SingletonServiceBean()
            {
               logger.info("SingletonServiceBean constructor invoked.");
            }


     @PostConstruct
     public void performOperations()
	    {
               logger.info("SingletonServiceBean performOperations() invoked.");
               try{   
                     MBeanServer mbeanServer = java.lang.management.ManagementFactory.getPlatformMBeanServer(); 
                     logger.info("Got the MBeanServer.... "+mbeanServer);
                     ObjectName objectName=new ObjectName("test.startup:service=HelloWorldMXBean");
                     HelloWorldServiceMXBean bean=new HelloWorldService();
                     mbeanServer.registerMBean(bean, objectName); 
                     logger.info("MXBean Registered with ObjectName:  "+objectName); 
                  }
               catch(Exception e) { e.printStackTrace(); }
	    }


	@PreDestroy
	public void cleanUp() throws Exception 
         {
	    logger.info("cleaning up MXBeans.");
            try{   
                     MBeanServer mbeanServer = java.lang.management.ManagementFactory.getPlatformMBeanServer(); 
                     ObjectName objectName=new ObjectName("test.startup:service=HelloWorldMXBean");
                     mbeanServer.unregisterMBean(objectName); 
                     logger.info("MXBean *Unregistered* with ObjectName:  "+objectName); 
               }
             catch(Exception e) { e.printStackTrace(); }
         }
   }
