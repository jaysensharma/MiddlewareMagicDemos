package servlets;
import java.io.IOException;
import java.io.PrintWriter;

//servlet-api
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//log4j
import org.apache.log4j.Logger;

@WebServlet(value="/TestServlet")
public class TestServlet extends HttpServlet 
  {
	private static final long serialVersionUID = 1L;
        private static Logger logger = null;

        public void init() throws ServletException {
         logger= Logger.getLogger(TestServlet.class);   
        }

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out=response.getWriter();
                        String logLevel=request.getParameter("logLevel");
                        String logMessage=request.getParameter("logMessage");
                        out.println("<html><head><title>Log4j Logging Demo</title></head><body>");
                        if(logLevel.equals("TRACE"))
                           {
                             out.println("["+logLevel+"] "+logMessage+" (logged inside log file).");
                             logger.trace(logMessage);
                           }
                        if(logLevel.equals("DEBUG"))
                           {
                             out.println("["+logLevel+"] "+logMessage+" (logged inside log file).");
                             logger.debug(logMessage);
                           }
                        if(logLevel.equals("INFO"))
                           {
                             out.println("["+logLevel+"] "+logMessage+" (logged inside log file).");
                             logger.info(logMessage);
                           }
                        if(logLevel.equals("WARN"))
                           {
                             out.println("["+logLevel+"] "+logMessage+" (logged inside log file).");
                             logger.warn(logMessage);
                           }

                        out.println("<BR><BR><a href=\"index.jsp\"> Want a different logging level? </a></body></html>");
		     } 
                 catch (Exception e) 
                     {
			e.printStackTrace();
		     }
	}
}
