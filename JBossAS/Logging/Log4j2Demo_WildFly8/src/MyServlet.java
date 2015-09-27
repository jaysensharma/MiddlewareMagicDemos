package servlets;
import java.util.*;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.PrintWriter;
import org.apache.log4j.Logger;

public class MyServlet extends HttpServlet {

  private static Logger logger = Logger.getLogger(MyServlet.class);

  public void  init(ServletConfig config)  throws ServletException {
      System.out.println("[MyServlet] In servlet init");  
    }
 
  public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
       PrintWriter out = response.getWriter();
       out.println("<h1>Hello!! MyServlet says it is *** GET request.");
       System.out.println("[MyServlet] responding to *** GET request.");
       logger.info("Just some sample INFO messages");
       logger.debug("Just some sample DEBUG messages");
       logger.error("Just some sample ERROR messages");
  }
}
