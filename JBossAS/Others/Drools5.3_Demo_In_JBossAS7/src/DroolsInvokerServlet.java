package servlets;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
 
@WebServlet(value="/DroolsInvokerServlet")
public class DroolsInvokerServlet extends HttpServlet
  {
     public void service(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
       {
            PrintWriter out=res.getWriter();
            System.out.println("[DroolsInvokerServlet] Inside service() of DroolsInvokerServlet.");
            try
             {
               String ruleFile = "testRule.drl";
               System.out.println("[DroolsInvokerServlet] ruleFile = "+ruleFile);
               engine.MyRuleEngine.executeRuleEngile(ruleFile,"Hello World");
               out.println("Check JBoss Console for the Output of Drools Execution.");
             }
            catch(Exception e)
             {
                 System.out.println("[DroolsInvokerServlet] throws Exception: "+e);
                 e.printStackTrace();
             }
            System.out.println("Exiting service() of DroolsInvokerServlet.");
       }
  }
