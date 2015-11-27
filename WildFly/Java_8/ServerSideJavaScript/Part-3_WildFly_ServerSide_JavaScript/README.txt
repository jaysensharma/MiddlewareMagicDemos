This is a simple demo explaining how to use the Nashorn Server Side Java Script On WildFly. See: 

Server Side Java Script Nashorn, WildFly10, Undertow with MySQL
http://middlewaremagic.com/jboss/?p=2770



To know more about the Nashorn please refer to the previous continuation of this demo:

Prerequisite Articles:
######################
Why Nashorn Java Scripting in Java8, Is it Poisonous for Java? (part-1)
http://middlewaremagic.com/jboss/?p=2751

JDK8 Based Nashorn JavaScript & Java to interact with MySQL Database. (part-2)
http://middlewaremagic.com/jboss/?p=2760



WildFly Side Configuration
##########################
Create DataSource as following using CLI:

Install Driver as a module
--------------------------
module add --name=com.mysql  --dependencies=javax.api,javax.transaction.api --resources=/PATH/TO/mysql-connector-java-5.1.35.jar 
 

Install JDBC driver
--------------------
/subsystem=datasources/jdbc-driver=mysql/:add(driver-module-name=com.mysql,driver-name=mysql,jdbc-compliant=false,driver-class-name=com.mysql.jdbc.Driver) 


Create DataSource
------------------
/subsystem=datasources/data-source="MySqlDS":add(jndi-name="java:jboss/MySqlDS",driver-name="mysql",connection-url="jdbc:mysql://localhost:3306/TestJavaScriptDB",user-name="root",password=“testpwd”)