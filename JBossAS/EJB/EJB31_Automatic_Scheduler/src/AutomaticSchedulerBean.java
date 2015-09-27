package test;
import javax.ejb.Stateless;
import java.util.Date;
import javax.ejb.Schedule;

@Stateless(name="AutomaticSchedulerBean")
public class AutomaticSchedulerBean
 {
     @Schedule(dayOfWeek = "*", hour = "*", minute = "*", second = "*/5",year="2012", persistent = false)
     public void backgroundProcessing()
	    {
		   System.out.println("\n\n\t AutomaticSchedulerBean's backgroundProcessing() called....at: "+new Date());
	    }
 }


