package ejb3;
import javax.ejb.*;
@Remote
public interface HelloRemote {
        public String sayHello(String name);
}
