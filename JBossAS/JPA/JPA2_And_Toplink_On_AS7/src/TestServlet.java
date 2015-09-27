package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Integer;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.transaction.*;
import javax.annotation.*;
import jpa.Employee;

@WebServlet(value="/TestServlet")
public class TestServlet extends HttpServlet 
  {
	private static final long serialVersionUID = 1L;


        @Resource private UserTransaction 
        utx;
 
        @PersistenceContext(unitName="EmployeeService")
	EntityManager em;
	  
        @EJB(lookup="java:global/JPA_BASIC_Demo/EmployeeService!jpa.EmployeeServiceRemote")
        jpa.EmployeeServiceRemote remote;


	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                PrintWriter out=null;
		try {
			System.out.println("inside TestServlet service()");
			System.out.println("GOT the UserTransaction       :  "+utx);
			System.out.println("Got jpa.EmployeeServiceRemote :  "+remote);
			out=response.getWriter();			
			
                        remote.createEmployee(new Integer(request.getParameter("empId")),request.getParameter("empName"));

                        out.println("<html><head><title>JPA Insert</title></head><body>");
			out.println("<BR>Employee Record Inserted in the Database.<BR>");
                        out.println("<TABLE border=\"10%\">");
                        out.println("<TR><TD>EmpID</TD><TD>EmpName</TD></TR>");
			for(Employee tmp:remote.findAllEmployees())
			{
			   out.println("<TR><TD>"+tmp.getEmpno()+"</TD><TD>"+tmp.getEname()+"</TD></TR>");
			}
                        out.println("</TABLE>");
                        out.println("<BR><BR><a href=\"index.jsp\"> Want to insert Some More Employee Records ?</a></body></html>");
		     } 
                 catch (Exception e) 
                     {
			e.printStackTrace();
		     }
	}
}
