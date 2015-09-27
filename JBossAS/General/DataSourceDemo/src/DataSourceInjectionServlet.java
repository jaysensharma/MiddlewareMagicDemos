package servlets;
import java.io.IOException;
import java.io.PrintWriter;

//servlet-api
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// SQL related
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

//commons logging
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;


@WebServlet(value="/DataSourceInjectionServlet")
public class DataSourceInjectionServlet extends HttpServlet 
  {
        @Resource(name = "java:jboss/exported/MySqlDS")
        private DataSource dataSource;

        private Connection con;
        //commons logging
        private static Log logger = LogFactory.getLog(DataSourceInjectionServlet.class);

        public void init() throws ServletException {
            logger.info("Successfully retrieved JDBC Connection from DataSource dataSource =  "+dataSource);
        }

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
                        con=dataSource.getConnection();
                        logger.info("Successfully retrieved JDBC Connection =  "+con);
			PrintWriter out=response.getWriter();
                        out.println("[resource-ref injection] Successfully retrieved JDBC Connection from DataBase, con =  "+con);
                        DatabaseMetaData databaseMetaData = con.getMetaData();
                        out.println("<BR>databaseMetaData.getDatabaseProductName() = "+databaseMetaData.getDatabaseProductName());
                        out.println("<BR>databaseMetaData.getDatabaseProductVersion() = "+databaseMetaData.getDatabaseProductVersion());
                        out.println("<BR>databaseMetaData.getDriverMajorVersion() = "+databaseMetaData.getDriverMajorVersion());
                        out.println("<BR>databaseMetaData.getDriverMinorVersion() = "+databaseMetaData.getDriverMinorVersion());
                        closeConnection();
		     } 
                 catch (Exception e) 
                     {
			e.printStackTrace();
		     }
	}

        public void closeConnection() {
            if(con!=null) 
                {
                  try{
                       con.close();
                       logger.info("Jdbc Connection =  "+con+" Destroyed Successfully");
                     }
                   catch(Exception e)
                    {
                       e.printStackTrace();
                    }
                   finally
                    {
                       if(con!=null)
                        {
                           try{   con.close();  logger.info("finally{} Jdbc Connection =  "+con+" Destroyed Successfully"); }
                           catch(Exception ee){  ee.printStackTrace(); }
                       }
                    }
              }
       }
}
