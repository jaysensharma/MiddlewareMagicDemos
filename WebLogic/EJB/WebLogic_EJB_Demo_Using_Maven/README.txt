This example shows how to deploy a Simple EJB based application on WebLogic 12c using Maven based approach.



How to build and deploy project:
################################

$ export M2_HOME=/PATH/TO/apache_maven_3.2.3
$ export JAVA_HOME=/PATH/TO/jdk1.8.0_60
$ export PATH=$JAVA_HOME/bin:/PATH/TO/apache_maven_3.2.3/bin:$PATH
$ cd WebLogic_EJB_Demo_Using_Maven

mvn clean install wls:deploy -s settings.xml



In order to know how to use “Weblogic 12c specific new wls-maven-plugin” 
please see:
http://middlewaremagic.com/weblogic/?p=8217
