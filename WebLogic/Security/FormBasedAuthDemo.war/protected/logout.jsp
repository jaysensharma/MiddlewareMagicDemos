<%@page import="java.util.*, java.lang.reflect.*, javax.servlet.http.HttpSession "%>
<%@ page session="false" %>
<% 
        HttpSession session=request.getSession(false);
        session.invalidate();
%>

   <a href="../login.jsp">Logout Successful !!! Click here to login </a>

