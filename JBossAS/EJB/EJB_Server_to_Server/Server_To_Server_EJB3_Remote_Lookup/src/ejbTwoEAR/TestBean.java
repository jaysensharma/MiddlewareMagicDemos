package ejb.two;
import javax.ejb.*;
import javax.naming.*;
import java.util.*;

@Stateless  
@Remote(TestRemote.class)
public class TestBean implements TestRemote
 {
     public String helloWorld(String name)
	    {
		   System.out.println("\n\n\t Bean's helloWorld(String name) called....");
		   return "[TestBean] returned Hello "+name;
	    }
 }
