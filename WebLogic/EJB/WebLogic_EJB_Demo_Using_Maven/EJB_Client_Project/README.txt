This example shows how to run the Simple WebLogic EJB Client based application using Maven based approach.



How to build and deploy project:
################################

$ export M2_HOME=/PATH/TO/apache_maven_3.2.3
$ export JAVA_HOME=/PATH/TO/jdk1.8.0_60
$ export PATH=$JAVA_HOME/bin:/PATH/TO/apache_maven_3.2.3/bin:$PATH
$ cd WebLogic_EJB_Demo_Using_Maven/EJB_Project


 mvn clean install exec:exec 



NOTE: Please read the "pom.xml" carefully and alter the  <!-- CHANGE ME !!! --> declared lines according to your setup.


	