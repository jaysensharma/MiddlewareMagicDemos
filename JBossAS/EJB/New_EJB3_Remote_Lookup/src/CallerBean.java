package remote;
import javax.ejb.*;
import javax.naming.*;
import java.util.*;

@Stateless  
@Remote(CallerRemote.class)
public class CallerBean implements CallerRemote
 {
     public String testMethod(String name)
	    {
		   System.out.println("\n\n\t Bean's testMethod(String name) called....");
		   return "[CallerBean] returned Hello "+name;
	    }
 }
