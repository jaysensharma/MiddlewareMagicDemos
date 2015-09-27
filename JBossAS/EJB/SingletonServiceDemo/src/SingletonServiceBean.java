package sar.dependency.alternative;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//commons logging
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// DataSource related
import javax.sql.DataSource;
import java.sql.Connection;

// Resource Injection related
import javax.annotation.Resource;

@Startup
@Singleton  

public class SingletonServiceBean
 {
     private static Log logger = LogFactory.getLog(SingletonServiceBean.class);
     Connection con=null;


     public SingletonServiceBean()
            {
               logger.info("SingletonServiceBean constructor invoked.");
            }


     @Resource(name = "java:jboss/datasources/MySqlDS")
     DataSource ds;
     /* This @PostConstruct annotation is required to tell JBoss to invoke the "performOperations" immediately after the "SingletonServiceBean" creation. */
     @PostConstruct
     public void performOperations()
	    {
               logger.info("SingletonServiceBean performOperations() invoked.");
               try{   
                     con=ds.getConnection();
                     logger.info("***** DataSource = "+ds+" . Connection con = "+con+" retrieved from the ConnectionPool.*****");
                  }
               catch(Exception e) 
                 {
                     logger.error("Unable to get Connection From DataSource = "+ds+" ERROR: "+e.getMessage()); 
                     e.printStackTrace(); 
                 }
	    }


	@PreDestroy
	public void cleanUp() throws Exception {
	  logger.info("cleaning up connections.");
          if(con!=null)
            {
               try{   
                     con.close(); 
                     logger.info("Connection con = "+con+" is successfully returned to the ConnectionPool.");
                  }
               catch(Exception e) 
                 {
                     logger.error("Unable to Close Connection con = "+con+" ERROR: "+e.getMessage()); 
                     e.printStackTrace(); 
                 }
               finally{  
                        try{   con.close(); }
                        catch(Exception ee) { ee.printStackTrace(); }
                      }
            }
	}
 }
