This example demonstrates how to use the Virtual Directory mapping in WebLogic 12c.

We are placing some static contents in some directory like "/Users/jsensharma/Pictures" and with the help of WebLogic feature "virtual-directory-mapping" we are allowing a JSP / servlet deployed on WLS to access those static resources.

See: <a href="http://middlewaremagic.com/weblogic/?p=790">Virtual Directory mapping in WebApps</a>


<?xml version="1.0" encoding="ISO-8859-1"?>
<weblogic-web-app  xmlns="http://xmlns.oracle.com/weblogic/weblogic-web-app" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
                   xsi:schemaLocation="http://xmlns.oracle.com/weblogic/weblogic-web-app 
                   http://xmlns.oracle.com/weblogic/weblogic-web-app/1.6/weblogic-web-app.xsd">  <!-- Weblogic 12c -->
	<virtual-directory-mapping>
		<local-path>/Users/jsensharma/Pictures</local-path>
		<url-pattern>*.jpg</url-pattern>
		<url-pattern>*.png</url-pattern>
		<url-pattern>/static/*</url-pattern>
	</virtual-directory-mapping>
</weblogic-web-app>


And the JSP is going to access it as following:

<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<html>
  <body>
     <center><h1> Hello</h1></center>
     <img src="MM-Banner-logo.png"/>
     <HR>
     <img src="static/MiddlewareMagic_Logo.jpg"/>
   </body>
</html>
