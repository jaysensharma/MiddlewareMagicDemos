package ejb3;
import javax.ejb.*;
import javax.naming.*;
import java.util.*;
@Stateless  
@Remote(CallerRemote.class)
public class CallerBean implements CallerRemote {
     public String testMethod(String name) {
          String result="";
		  System.out.println("\n\n\t[CallerBean] Bean's testMethod(String name) called...."); 
		  return "[CallerBean] returned Welcome, "+name;
	 }
 }
