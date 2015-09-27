package beans;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.TreeMap;
import test.Customer;

@Named
@ApplicationScoped
public class TestBean implements Serializable
   {
    private static final long serialVersionUID = 1L;
    private TreeMap<Integer, Customer> customerMap;
 
    public TestBean()
         {
           System.out.println("\n\t TestBean which is CDI Bean instantiated.");
           customerMap=new TreeMap<Integer, Customer>();
         }
 
    public TreeMap<Integer, Customer> getCustomerMap()
         {
            System.out.println("\n\t[TestBean] getCustomerMap() called.");
            return customerMap;
         }
 
    public void setCustomerMap(TreeMap<Integer, Customer> customerMap)
         {
           System.out.println("\n\t[TestBean] setCustomerMap(TreeMap<Integer, Customer>) called.");
           this.customerMap = customerMap;
         }
   }
