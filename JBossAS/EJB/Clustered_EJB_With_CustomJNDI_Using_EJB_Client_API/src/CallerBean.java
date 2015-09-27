package remote;
import javax.ejb.*;
import javax.naming.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

import org.jboss.ejb3.annotation.Clustered;

@Stateless
//@StatefulTimeout(value = 1000L, unit = TimeUnit.MILLISECONDS)
@Clustered
@Remote(CallerRemote.class)
public class CallerBean implements CallerRemote
 {
     private int count = 0;
     public String testMethod(String name)
	    {
		   System.out.println("\n\n\t Bean's testMethod(String name) called....");
		   return "[CallerBean] returned Hello "+name;
	    }

     public String increment()
	    {
		   System.out.println("\n\n\t Bean's increment called....");
		   return "[CallerBean]  count = "+ (++count);
	    }
 }
