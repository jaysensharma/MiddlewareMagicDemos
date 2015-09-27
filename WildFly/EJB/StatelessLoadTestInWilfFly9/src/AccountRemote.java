package ejb3;
import javax.ejb.Remote;
@Remote
public interface AccountRemote{
     public String helloWorld(String name);
}
