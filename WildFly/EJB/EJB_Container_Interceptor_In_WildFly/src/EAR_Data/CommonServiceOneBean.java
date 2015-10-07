package ejb.service.one;
import javax.ejb.*;
import javax.naming.*;

@Stateless  
@Remote(CommonServiceOne.class)   
public class CommonServiceOneBean implements CommonServiceOne {
     public String helloWorld(String name) {
		   System.out.println("\n\n\t[CommonServiceOneBean] helloWorld(String "+name+") invoked.");
		   return "[CommonServiceOneBean] returned Hello "+name;
	    }
 }
