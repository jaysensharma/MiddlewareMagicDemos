package ejb3;
import javax.ejb.Remote;

@Remote
public interface MathRemote {
	public int add(int x, int y);
	public String sayHello(String name);
}
