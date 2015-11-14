This is a very simple example which demonstrates how to deploy an “Apache CXF“ based web service in “WebLogic 12.2.1” (12c)

We are using the following component versions for this demo:

Components
----------
1. WebLogic 12.2.1
2. Apache CXF 3.1.4
3. Spring 4.1.4.RELEASE


After successful deployment of the build WAR of this project the following URLs can be accessed on WebLogic:

http://localhost:7001/WebLogic_CXF_Demo/services
http://localhost:7001/WebLogic_CXF_Demo/services/HelloWorld?wsdl



####################################
How to deploy and run this project?

1. Users must know how to use the “weblogic-maven-plugin” plugin.     
              <groupId>com.oracle.weblogic</groupId> 
              <artifactId>weblogic-maven-plugin</artifactId> 
              <version>12.2.1-0-0</version> 

   If not aware then please refer to :  
   How to use WebLogic 12c provided Maven Synchronization Plug-In ? 
   http://middlewaremagic.com/weblogic/?p=8294


2). In order to clean and build the project WAR do the following:

cd WebLogic12c_CXF_Demo_Maven
mvn clean install 


3). In order to deploy the application run the following maven command:
mvn weblogic:deploy

**NOTE:** Make sure to change the username and password of web logic server in “pom.xml” file.



