package ejb.service;
import javax.ejb.*;
import javax.naming.*;

@Stateless  
@Remote(CommonService.class)   
public class CommonServiceBean implements CommonService {
     public String helloWorld(String name) {
		   System.out.println("\n\n\t[CommonServiceBean] helloWorld(String "+name+") invoked.");
		   return "[CommonServiceBean] returned Hello "+name;
	    }
 }