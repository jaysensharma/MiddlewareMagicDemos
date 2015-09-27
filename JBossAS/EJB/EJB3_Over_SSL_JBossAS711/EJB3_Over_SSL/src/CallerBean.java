package remote;
import javax.ejb.*;
import javax.naming.*;
import java.util.*;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;

@Stateless  
@Remote(CallerRemote.class)
//@org.jboss.ejb3.annotation.SecurityDomain("ejb-security-domain")

public class CallerBean implements CallerRemote
 {
     @Resource
     private SessionContext sessionContext;

     @RolesAllowed("ejbRole")
     public String testMethod(String name)
	    {
		   System.out.println("\n\n\tBean's testMethod(String name) called....");
                   System.out.println("\n\n\tUserName: '" + sessionContext.getCallerPrincipal().getName() + "' is able to access testMethod()");
                   if (sessionContext.isCallerInRole("ejbRole"))
                         System.out.println("\tUser is in   \"ejbRole\"   ");
                   else
                         System.out.println("\tUser is NOT in an allowed role");
		   return "[CallerBean] testMethod() returned Hello "+name;
	    }


     public String commonMethod(String name)
	    {
		   System.out.println("\n\n\tBean's commonMethod(String name) called....");
                   System.out.println("\n\nUser " + sessionContext.getCallerPrincipal().getName() + " is able to access commonMethod()");
                   if (sessionContext.isCallerInRole("ejbRole"))
                         System.out.println("\t##### commonMethod()  User is in ejbRole");
                   else
                         System.out.println("\t##### commonMethod()  User is NOT in ejbRole");
		   return "[CallerBean] commonMethod() returned Hello "+name;
	    }
 }
