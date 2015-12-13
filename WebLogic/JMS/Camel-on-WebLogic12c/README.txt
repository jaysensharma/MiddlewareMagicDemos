For more detail on this demo please visit:
http://middlewaremagic.com/weblogic/?p=8334



This example shows how to use the Apache Camel Servlet based WAR inside WebLogic 12c (12.2.1) container.

The Camel Servlet will accept two numbers from the User and then it will use the Camel JMS Component Endpoint to send the generated output to a JMS Queue.

How to create JMS Queue using WLST:
###################################
Simply run the WLST script as following:


java weblogic.WLST  configureJMS.py 

Make sire to edit the “domain.properties” and place it in the same directory as the above PY script.


How to build and deploy
########################

cd CamelServlet_With_JMSEndpoint_Demo

mvn clean install 


### Following command will deploy the WAR on WebLogic 12c.
mvn weblogic:deploy

NOTE: if the above command is not working at your end then please see the article: http://middlewaremagic.com/weblogic/?p=8294


How to access the application:
##############################
http://localhost:8181/HelloCamelServletWebApp/index.html


