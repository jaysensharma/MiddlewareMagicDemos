import org.jboss.dmr.ModelNode;
import org.jboss.dmr.Property;
import org.jboss.as.controller.client.ModelControllerClient;

public class TestStandaloneModel
  {
   public void getWebSubsystemRuntimeDetails(ModelControllerClient client)
    {
       System.out.println("\n\n\n************************************************");
       System.out.println("Web Subsystem Runtime Details");
       System.out.println("************************************************");
       try{
             ModelNode op = new ModelNode(); 
             op.get("operation").set("read-resource"); 
             ModelNode address = op.get("address"); 
             address.add("subsystem", "web"); 
             address.add("connector", "http"); 
             op.get("recursive").set(true); 
             op.get("include-runtime").set(true); 
             op.get("operations").set(true);
 
             ModelNode returnVal = client.execute(op); 
             String result=returnVal.get("result").toString();
             String path="/subsystem=web/connector=http";
             if(result.equals("undefined")) 
                {
                   System.out.println("\n\tCheck ["+path+"] ** Server may not be Running");
                }
             else
                {
                   System.out.println(returnVal.get("result").toString());
                }
          }
        catch(Exception e)
          {
             e.printStackTrace();
             System.out.println("getWebSubsystemRuntimeDetails Failed: "+e);
          }
    }


   public void testNonXADataSource(ModelControllerClient client,String dataSourceName)
    {
       System.out.println("\n\n\n************************************************");
       System.out.println("DataSource \""+dataSourceName+"\" TestResults");
       System.out.println("************************************************");

       try{
             ModelNode op = new ModelNode(); 
             op.get("operation").set("test-connection-in-pool"); 
             ModelNode address = op.get("address"); 
             address.add("subsystem", "datasources"); 
             address.add("data-source", dataSourceName); 
             op.get("operations").set(true);
 
             ModelNode returnVal = client.execute(op); 
             String result=returnVal.get("result").toString();
             String path="/subsystem=datasources/data-source="+dataSourceName;
             if(result.equals("[true]")) 
                {
                   System.out.println("\n\tNon XA DataSource \""+dataSourceName+"\" is RUNNING Successfully.");
                }
             else if(result.equals("[false]")) 
                {
                   System.out.println("\n\tNon XA DataSource \""+dataSourceName+"\" is TEST FAILED Successfully. Check PATH exist or not? ["+path+"]");
                }
             else if(result.equals("undefined")) 
                {
                   System.out.println("\n\tDataSource \""+dataSourceName+"\" might not be deployed, ["+path+"]  ** Server may not be Running"+"Please Check the DataSource \""+dataSourceName+"\" name, It may be INCORRECT. \n\tCheck PATH exist or not? ["+path+"]");
                }
          }
        catch(Exception e)
          {
             e.printStackTrace();
             System.out.println("testNonXADataSource Failed: "+e);
          }
    }


   public void monitorApplicationStatistics(ModelControllerClient client,String applicationName)
    {
       System.out.println("\n\n\n******************************************************");
       System.out.println("Application \""+applicationName+"\" RuntimeStatistics");
       System.out.println("******************************************************");
       try{
             ModelNode op = new ModelNode(); 
             op.get("operation").set("read-resource"); 
             ModelNode address = op.get("address"); 
             address.add("deployment", applicationName); 
             op.get("recursive").set(true); 
             op.get("include-runtime").set(true); 
             op.get("include-defaults").set(true); 
             op.get("operations").set(true);
 
             ModelNode returnVal = client.execute(op); 
             String result=returnVal.get("result").toString();
             String path="/deployment="+applicationName;
             if(result.equals("undefined")) 
                {
                   System.out.println("\n\tApplication \""+applicationName+ "\" might not have deployed. ** Server may not be Running. Check PATH exist or not? ["+path+"]");
                }
             else
                {
                   System.out.println("\n\tMonitoring \""+applicationName+ "\""+result);
                }
          }
        catch(Exception e)
          {
             e.printStackTrace();
             System.out.println("testNonXADataSource Failed: "+e);
          }
    }
  }
