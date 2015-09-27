Step-1). Make sure that the Spring module is deployed on the JBoss AS7  (if you won't configure the spring module on your JBossAS7 separately then the WEB-INF/jbossws-cxf.xml  file will be ignored by JBoss)

    See the Module "modules"

$JBOSS_HOME/modules/
org
 └──springframework
        └── spring
                └── main
                    ├── module.xml
                    ├── org.springframework.aop-3.0.6.RELEASE.jar
                    ├── org.springframework.asm-3.0.6.RELEASE.jar
                    ├── org.springframework.aspects-3.0.6.RELEASE.jar
                    ├── org.springframework.beans-3.0.6.RELEASE.jar
                    ├── org.springframework.context-3.0.6.RELEASE.jar
                    ├── org.springframework.context.support-3.0.6.RELEASE.jar
                    ├── org.springframework.core-3.0.6.RELEASE.jar
                    ├── org.springframework.expression-3.0.6.RELEASE.jar
                    ├── org.springframework.instrument-3.0.6.RELEASE.jar
                    ├── org.springframework.instrument.tomcat-3.0.6.RELEASE.jar
                    ├── org.springframework.jdbc-3.0.6.RELEASE.jar
                    ├── org.springframework.jms-3.0.6.RELEASE.jar
                    ├── org.springframework.orm-3.0.6.RELEASE.jar
                    ├── org.springframework.oxm-3.0.6.RELEASE.jar
                    ├── org.springframework.spring-library-3.0.6.RELEASE.libd
                    ├── org.springframework.test-3.0.6.RELEASE.jar
                    ├── org.springframework.transaction-3.0.6.RELEASE.jar
                    ├── org.springframework.web-3.0.6.RELEASE.jar
                    ├── org.springframework.web.portlet-3.0.6.RELEASE.jar
                    ├── org.springframework.web.servlet-3.0.6.RELEASE.jar
                    └── org.springframework.web.struts-3.0.6.RELEASE.jar


module.xml will look like following:
====================================
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.1" name="org.springframework.spring" slot="main">
	<resources>
		<resource-root path="org.springframework.beans-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.aop-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.core-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.asm-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.context-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.expression-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.aspects-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.context.support-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.instrument-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.jdbc-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.jms-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.orm-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.oxm-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.transaction-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.web-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.web.portlet-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.web.servlet-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.web.struts-3.0.6.RELEASE.jar"/>
		<resource-root path="org.springframework.test-3.0.6.RELEASE.jar"/>
                <resource-root path="org.springframework.transaction-3.0.6.RELEASE.jar"/>
                <resource-root path="org.springframework.instrument.tomcat-3.0.6.RELEASE.jar"/>
                <!-- <resource-root path="org.springframework.binding-2.2.0.RELEASE.jar"/>
                <resource-root path="org.springframework.faces-2.2.0.RELEASE.jar"/>
                <resource-root path="org.springframework.js.resources-2.2.0.RELEASE.jar"/>    	
                <resource-root path="org.springframework.js-2.2.0.RELEASE.jar"/>
                <resource-root path="org.springframework.webflow-2.2.0.RELEASE.jar"/> -->
	</resources>
	<dependencies>
		<module name="javaee.api" export="true"/>
		<module name="org.apache.commons.logging"/>
		<module name="org.jboss.vfs"/> 
	</dependencies>
</module>







Step-2). Deploy the Attached Application as following:

[jsenshar@localhost jbossws-cxf.xml_Demo]$ ant
Buildfile: build.xml

init:
   [delete] Deleting directory /home/jaysensharma/jbossws-cxf.xml_Demo/build
    [mkdir] Created dir: /home/jaysensharma/jbossws-cxf.xml_Demo/build
    [mkdir] Created dir: /home/jaysensharma/jbossws-cxf.xml_Demo/build/jaxws-cxfXMLdemo
    [mkdir] Created dir: /home/jaysensharma/jbossws-cxf.xml_Demo/build/jaxws-cxfXMLdemo/META-INF
    [mkdir] Created dir: /home/jaysensharma/jbossws-cxf.xml_Demo/build/jaxws-cxfXMLdemo/WEB-INF
    [mkdir] Created dir: /home/jaysensharma/jbossws-cxf.xml_Demo/build/jaxws-cxfXMLdemo/WEB-INF/classes
   [delete] Deleting directory /home/jaysensharma/jbossws-cxf.xml_Demo/clientStuff
    [mkdir] Created dir: /home/jaysensharma/jbossws-cxf.xml_Demo/clientStuff/META-INF

build:
    [javac] Compiling 1 source file to /home/jaysensharma/jbossws-cxf.xml_Demo/build/jaxws-cxfXMLdemo/WEB-INF/classes
     [copy] Copying 2 files to /home/jaysensharma/jbossws-cxf.xml_Demo/build/jaxws-cxfXMLdemo/WEB-INF
[wsprovide] Generating from endpoint: ws.DemoCXF
[wsprovide] log4j:WARN No appenders could be found for logger (org.apache.cxf.common.logging.LogUtils).
[wsprovide] log4j:WARN Please initialize the log4j system properly.
[wsprovide] log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
[wsprovide] java2ws -s /home/jaysensharma/jbossws-cxf.xml_Demo/build -classdir /home/jaysensharma/jbossws-cxf.xml_Demo/build -d /home/jaysensharma/jbossws-cxf.xml_Demo/build/jaxws-cxfXMLdemo/WEB-INF/wsdl -verbose -wsdl -cp ::::::::: -wrapperbean -createxsdimports ws.DemoCXF
[wsprovide] java2ws - Apache CXF 2.4.6-redhat-1
[wsprovide] 
      [jar] Building jar: /home/jaysensharma/jbossws-cxf.xml_Demo/build/jaxws-cxfXMLdemo.war

deploy:
     [echo] *******************  Deploying   *********************
     [echo] ********** jaxws-cxfXMLdemo.war to /NotBackedUp/JBoss_All/jboss-as-7.1.2.Final/standalone/deployments **********
     [copy] Copying 1 file to /NotBackedUp/JBoss_All/jboss-as-7.1.2.Final/standalone/deployments
     [echo] *******************  Deployed Successfully   *********************

BUILD SUCCESSFUL
Total time: 4 seconds
[jsenshar@localhost jbossws-cxf.xml_Demo]$ ant run
Buildfile: build.xml

post-deploy:
     [echo] *******************  NOTE  *********************
     [echo] ***** You should be able to access your WSDL using Browser now *****
     [echo]                 http://localhost:8080/jaxws-cxfXMLdemo?wsdl          

client:
   [delete] Deleting directory /home/jaysensharma/jbossws-cxf.xml_Demo/clientStuff
    [mkdir] Created dir: /home/jaysensharma/jbossws-cxf.xml_Demo/clientStuff
[wsconsume] Consuming wsdl: http://localhost:8080/jaxws-cxfXMLdemo?wsdl
[wsconsume] log4j:WARN No appenders could be found for logger (org.apache.cxf.common.logging.LogUtils).
[wsconsume] log4j:WARN Please initialize the log4j system properly.
[wsconsume] log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.
     [copy] Copying 1 file to /home/jaysensharma/jbossws-cxf.xml_Demo/clientStuff
    [javac] Compiling 1 source file to /home/jaysensharma/jbossws-cxf.xml_Demo/clientStuff
      [jar] Building jar: /home/jaysensharma/jbossws-cxf.xml_Demo/clientStuff/DemoCXFClient.jar
   [delete] Deleting directory /home/jaysensharma/jbossws-cxf.xml_Demo/clientStuff/client
   [delete] Deleting: /home/jaysensharma/jbossws-cxf.xml_Demo/clientStuff/log4j.properties

run:
     [java] Mar 10, 2013 7:52:53 PM client.DemoCXFService <clinit>
     [java] INFO: Can not initialize the default wsdl from service.wsdl
     [java] 
     [java] 	 port.sayHello("MiddlewareMagic") = Hello JBossAS7 User: MiddlewareMagic

BUILD SUCCESSFUL
Total time: 9 seconds

