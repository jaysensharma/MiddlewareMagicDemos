package ejb3;
import javax.ejb.Stateless;
import ejb3.MathRemote;

@Stateless
public class MathBean implements MathRemote {

	public int add(int x, int y){
		int result=x+y;
		System.out.println("[MathBean] add(int x, int y) is called returning : " + result);
		return result;
	}
	
	public String sayHello(String name) {
	    String result = "[MathBean] Hello, " + name;
		System.out.println("[MathBean] sayHello(String "+name+") is called returning : " + result);
		return result;
	}
}
