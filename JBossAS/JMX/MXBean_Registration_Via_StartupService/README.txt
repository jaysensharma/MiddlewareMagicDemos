<h3> This demonstrates How to register an @MXBean using @Startup  EJB</h3> at the time of server startup sothat we will not need to invoke the following code manually on our own:

               try{   
                     MBeanServer mbeanServer = java.lang.management.ManagementFactory.getPlatformMBeanServer(); 
                     logger.info("Got the MBeanServer.... "+mbeanServer);
                     ObjectName objectName=new ObjectName("test.startup:service=HelloWorldMXBean");
                     HelloWorldServiceMXBean bean=new HelloWorldService();
                     mbeanServer.registerMBean(bean, objectName); 
                     logger.info("MXBean Registered with ObjectName:  "+objectName); 
                  }
               catch(Exception e) { e.printStackTrace(); } 
