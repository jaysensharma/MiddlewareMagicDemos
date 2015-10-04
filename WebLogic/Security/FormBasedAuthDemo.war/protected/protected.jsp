<%@page import="java.util.*, java.lang.reflect.*, javax.servlet.http.HttpSession "%>
<%@ page session="false" %>
<html>
<head>
</head>
<body>
<center><h1> Protected Page </h1>
<b><font color=maroon>Congrates!!! Your Login Is Successful...U are able to access the Secure Page.</font></b><BR>
__________________________***_________________________<BR>

<% 
        HttpSession session=request.getSession(false);
        String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort();
		String cPath = request.getContextPath();
		String sPath = request.getServletPath();

		String nodeId = System.getProperty("weblogic.Name");
		String hostName = System.getProperty("java.rmi.server.hostname");
		String variableString = (String)session.getAttribute("SESSION_TEST_VARIABLE");
		int count = 0;
		if(variableString != null) {
			count = Integer.parseInt(variableString);
			count++;
		}
	session.setAttribute("SESSION_TEST_VARIABLE", String.valueOf(count));
%>

<html>
<body bgcolor=white>
  <table columns=2>
	<tr><td><font color=blue>The sessionId is:		</font></td><td><%=session.getId() %></td>
	<tr><td><font color=blue>The sessionObj is:		 </font></td><td><%=session %></td>
	<tr><td><font color=blue>The nodeId is:		</font></td><td><%=nodeId %></td>
	<tr><td><font color=blue>The server hostName is:		</font></td><td><%=hostName %></td>
	<tr><td><font color=blue># of requests placed on session:		</font></td><td><%=count %></td>
  </table>
  
  <h3><a href="logout.jsp"> Click Here to Logout</a></h3>
</body>
</html>
</center>
</body>
</html>

