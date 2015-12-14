<%@page import="java.util.*, java.lang.reflect.*, javax.servlet.http.HttpSession "%>
<%@ page session="false" %>
<html>
    <body>
        <center>
        <h1>Are you able to see this page ? </h1>
        <h2><font color=maroon>Congratulations !!! your Secure Web Application is working !!!</font></h2>

        <% 
          HttpSession session=request.getSession(false);
          String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort();
	      String contextPath = request.getContextPath();
	      String servletPath = request.getServletPath();
	      out.println("<h3>ContextPath: "+ contextPath +" and servletPath: " + servletPath + "</h3>" );
        %>
        </center>
     </body>
</html>

