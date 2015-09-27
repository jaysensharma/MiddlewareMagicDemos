package ejb3;
import javax.ejb.*;
import javax.annotation.*;
import ejb3.AccountRemote;
@Stateless
public class AccountBean implements AccountRemote {
     public String helloWorld(String name)
	    {
                   try {
                      Thread.sleep(500); 
   		      System.out.println("\n\n\t Bean's helloWorld(String name) called....name : "+name);
                   } catch (Exception e) {
                     e.printStackTrace();
                   }
		   return "[AccountBean] returned Hello "+name;
	    }
}
