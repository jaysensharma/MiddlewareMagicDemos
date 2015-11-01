#############################################
 XPath based XML processing using Spring DSL
#############################################


Step-1). How to develop and deploy an EJB using Maven in WebLogic 12c.

Please refer to the demo: 

Writing WebLogic 12c based EJB 3.0 project and Standalone client using Maven Build
<a href=“http://middlewaremagic.com/weblogic/?p=8283”>http://middlewaremagic.com/weblogic/?p=8283</a>


Step-2). How to set the set environment variables before running the Apache Camel based EJB Client. 

<strong>For Unix Based OS</strong>
$ export M2_HOME=/PATH/TO/apache_maven_3.2.3
$ export JAVA_HOME=/PATH/TO/jdk1.8.0_60
$ export PATH=$JAVA_HOME/bin:/PATH/TO/apache_maven_3.2.3/bin:$PATH
$ cd /PATH/TO/Apache_Camel_As_WebLogic_EJB_Client


<strong>For Windows Based OS</strong>
$ set M2_HOME=C:\PATH\TO\apache_maven_3.2.3
$ set JAVA_HOME=C:\PATH\TO\jdk1.8.0_60
$ set PATH=%JAVA_HOME%/bin;C:\PATH\TO\apache_maven_3.2.3\bin;%PATH%
$ cd C:\Apache_Camel_As_WebLogic_EJB_Client


Step-3). Once the environment is set then run it like following:

   $ mvn clean camel:run
