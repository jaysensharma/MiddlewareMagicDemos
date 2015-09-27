Solution:
=========   
        Edit the "jboss-a-mq-6.0.0/etc/org.apache.karaf.management.cfg" file and change the "rmiRegistryHost = 0.0.0.0" to "rmiRegistryHost = 127.0.0.1"  Then restart the broker.

Uncomment the following lines from the following files:

"jboss-a-mq-6.0.0/etc/system.poroperties
----------------------------------------------------
activemq.jmx.user=admin
activemq.jmx.password=admin


"jboss-a-mq-6.0.0/etc/users.poroperties"
----------------------------------------------------
admin=admin,admin

"jboss-a-mq-6.0.0/etc/system.poroperties
----------------------------------------------------
activemq.jmx.user=admin
activemq.jmx.password=admin


**NOTE:  Make sure that following file "jboss-a-mq-6.0.0/etc/org.apache.activemq.webconsole.cfg" does not have the commented lines  (if it is then it should be uncommented)

webconsole.jmx.user=${activemq.jmx.user}
webconsole.jmx.password=${activemq.jmx.password}
webconsole.jms.user=${activemq.jmx.user}
webconsole.jms.password=${activemq.jmx.password}

=======================================================================


####################
For Spring 
####################
Step-1).
======== In the build.xml file just change the location of the following properties so that it can pick up required jars:

<property name="spring.lib" value="/NotBackedUp/ByJay/Spring_3.3.0_Library" />
<property name="activemq.home" value="/NotBackedUp/ByJay/jboss-a-mq-6.0.0" />


Step-2).
========  Run the spring message producer as following:
[jsensharma@dhcp223-76 XML_Message_Using_JmsTemplate]$ ant
Buildfile: /home/jaysensharma/SpringMessaging_Using_JmsTemplate/build.xml

init:
    [mkdir] Created dir: /home/jaysensharma/SpringMessaging_Using_JmsTemplate/tmp
   [delete] Deleting directory /home/jaysensharma/SpringMessaging_Using_JmsTemplate/build
    [mkdir] Created dir: /home/jaysensharma/SpringMessaging_Using_JmsTemplate/build

buildProducer:
    [javac] /home/jaysensharma/SpringMessaging_Using_JmsTemplate/build.xml:28: warning: 'includeantruntime' was not set, defaulting to build.sysclasspath=last; set to false for repeatable builds
    [javac] Compiling 1 source file to /home/jaysensharma/SpringMessaging_Using_JmsTemplate/tmp
     [copy] Copying 2 files to /home/jaysensharma/SpringMessaging_Using_JmsTemplate/tmp
      [jar] Building jar: /home/jaysensharma/SpringMessaging_Using_JmsTemplate/tmp/jmsSpringProducer.jar
     [copy] Copying 1 file to /home/jaysensharma/SpringMessaging_Using_JmsTemplate/build

runProducer:
   [delete] Deleting directory /home/jaysensharma/SpringMessaging_Using_JmsTemplate/tmp
     [java] 15-Oct-2013 17:28:53 org.springframework.context.support.AbstractApplicationContext prepareRefresh
     [java] INFO: Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@41ac1fe4: startup date [Tue Oct 15 17:28:53 IST 2013]; root of context hierarchy
     [java] 15-Oct-2013 17:28:53 org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions
     [java] INFO: Loading XML bean definitions from class path resource [producerApplicationContext.xml]
     [java] 15-Oct-2013 17:28:53 org.springframework.beans.factory.support.DefaultListableBeanFactory preInstantiateSingletons
     [java] INFO: Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@7ec5495e: defining beans [connectionFactory,testQueue,jmsTemplate]; root of factory hierarchy
     [java] SLF4J: Class path contains multiple SLF4J bindings.
     [java] SLF4J: Found binding in [jar:file:/home/jsensharma/NotBackedUp/ByJay/jboss-a-mq-6.0.0/system/org/fusesource/patch/patch-client/7.2.0/patch-client-7.2.0.jar!/org/slf4j/impl/StaticLoggerBinder.class]
     [java] SLF4J: Found binding in [jar:file:/home/jsensharma/NotBackedUp/ByJay/jboss-a-mq-6.0.0/system/org/ops4j/pax/logging/pax-logging-api/1.7.0/pax-logging-api-1.7.0.jar!/org/slf4j/impl/StaticLoggerBinder.class]
     [java] SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
     [java] 
     [java] 
     [java] 	[SpringQueueSender] main called and appContext : org.springframework.context.support.ClassPathXmlApplicationContext@41ac1fe4: startup date [Tue Oct 15 17:28:53 IST 2013]; root of context hierarchy
     [java] Sending message: Text-Generated At=> Tue Oct 15 23:39:17 IST 2013



BUILD SUCCESSFUL
Total time: 4 seconds


Step-3).
======== Now run the sporing Consumer as following   (With and without receiveTimeout property setting to -1):


[jsensharma@dhcp223-76 XML_Message_Using_JmsTemplate]$ ant runConsumer
Buildfile: /home/jaysensharma/SpringMessaging_Using_JmsTemplate/build.xml

buildConsumer:
    [mkdir] Created dir: /home/jaysensharma/SpringMessaging_Using_JmsTemplate/tmp
    [javac] /home/jaysensharma/SpringMessaging_Using_JmsTemplate/build.xml:40: warning: 'includeantruntime' was not set, defaulting to build.sysclasspath=last; set to false for repeatable builds
    [javac] Compiling 2 source files to /home/jaysensharma/SpringMessaging_Using_JmsTemplate/tmp
     [copy] Copying 2 files to /home/jaysensharma/SpringMessaging_Using_JmsTemplate/tmp
      [jar] Building jar: /home/jaysensharma/SpringMessaging_Using_JmsTemplate/tmp/jmsSpringConsumer.jar
     [copy] Copying 1 file to /home/jaysensharma/SpringMessaging_Using_JmsTemplate/build

runConsumer:
   [delete] Deleting directory /home/jaysensharma/SpringMessaging_Using_JmsTemplate/tmp
     [java] 15-Oct-2013 17:30:14 org.springframework.context.support.AbstractApplicationContext prepareRefresh
     [java] INFO: Refreshing org.springframework.context.support.ClassPathXmlApplicationContext@23174b07: startup date [Tue Oct 15 17:30:14 IST 2013]; root of context hierarchy
     [java] 15-Oct-2013 17:30:14 org.springframework.beans.factory.xml.XmlBeanDefinitionReader loadBeanDefinitions
     [java] INFO: Loading XML bean definitions from class path resource [consumerApplicationContext.xml]
     [java] 15-Oct-2013 17:30:14 org.springframework.beans.factory.support.DefaultListableBeanFactory preInstantiateSingletons
     [java] INFO: Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@2b03be0: defining beans [connectionFactory,myMessageListener,jmsContainer]; root of factory hierarchy
     [java] SLF4J: Class path contains multiple SLF4J bindings.
     [java] SLF4J: Found binding in [jar:file:/home/jsensharma/NotBackedUp/ByJay/jboss-a-mq-6.0.0/system/org/fusesource/patch/patch-client/7.2.0/patch-client-7.2.0.jar!/org/slf4j/impl/StaticLoggerBinder.class]
     [java] SLF4J: Found binding in [jar:file:/home/jsensharma/NotBackedUp/ByJay/jboss-a-mq-6.0.0/system/org/ops4j/pax/logging/pax-logging-api/1.7.0/pax-logging-api-1.7.0.jar!/org/slf4j/impl/StaticLoggerBinder.class]
     [java] SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
     [java] 15-Oct-2013 17:30:14 org.springframework.context.support.DefaultLifecycleProcessor$LifecycleGroup start
     [java] INFO: Starting beans in phase 2147483647
     [java] 
     [java] 
     [java] 	 [SpringQueueReceiver] main called and appContext : org.springframework.context.support.ClassPathXmlApplicationContext@23174b07: startup date [Tue Oct 15 17:30:14 IST 2013]; root of context hierarchy
     [java] Check if Listener is running or not ? The Spring Context will be closed within nextr 30 seconds.
     [java] MESSAGE TEXT: Text-Generated At=> Tue Oct 15 23:38:57 IST 2013
     [java] MESSAGE TEXT: Text-Generated At=> Tue Oct 15 23:39:12 IST 2013
     [java] MESSAGE TEXT: Text-Generated At=> Tue Oct 15 23:39:17 IST 2013




