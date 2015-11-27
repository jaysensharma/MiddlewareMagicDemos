import javax.script.*;
import java.io.*;

public class ExampleNashornJdbc {
	public static void main(String ar[]) throws Exception {
		ScriptEngineManager enginManager = new ScriptEngineManager();
		ScriptEngine engine = enginManager.getEngineByName("Nashorn");
		try {
			// Executing a simple java script file
			FileReader javaScriptFileReader = new FileReader(new File("testDB.js"));
            engine.eval(javaScriptFileReader);
            
		 } catch(final ScriptException se) {
			se.printStackTrace();
		 }
	}
}
