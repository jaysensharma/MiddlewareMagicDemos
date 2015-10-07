package ejb.service.two;
import javax.ejb.*;
import javax.naming.*;

@Stateless  
@Remote(CommonServiceTwo.class)   
public class CommonServiceTwoBean implements CommonServiceTwo
 {
     public String helloWorld(String name)
	    {
		   System.out.println("\n\n\t Bean's helloWorld(String name) called....");
		   return "[CommonServiceTwoBean] returned Hello "+name;
	    }
 }
