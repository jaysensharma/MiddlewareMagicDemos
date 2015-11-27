import javax.script.*;
import java.io.*;

public class ExampleNashorn {
	public static void main(String ar[]) throws Exception {
		ScriptEngineManager enginManager = new ScriptEngineManager();
        // This method takes one String argument with the name of the script engine. To get an instance of the Nashorn engine, pass in "nashorn". Alternatively, you can use any of the following: "Nashorn", "javascript", "JavaScript", "js", "JS", "ecmascript", "ECMAScript".
        // Creating a Java Script Engine
		ScriptEngine engine = enginManager.getEngineByName("Nashorn");
		
		try {
            //// Directly executing JavaScript snippet: ////
		    final String rawJavaScript =  "print(java.lang.System.getProperty(\"java.home\"));" +
                                          "var Date = Java.type(\"java.util.Date\");" +
                                          "var dt = new Date(); " +
                                          "print('dt = ' + dt);";
			engine.eval(rawJavaScript);

		
		    //// Mixing Java and JavaScript  ////
			engine.put("a", new Integer(ar[0]));
			engine.put("b", new Integer(ar[1]));
			engine.eval("var c = (a+b);");
			System.out.println("Sum : " + ((Number)engine.get("c")).intValue());


			//// Executing a simple java script file ////
			FileReader javaScriptFileReader = new FileReader(new File("test.js"));
            engine.eval(javaScriptFileReader);

		} catch(final ScriptException se) {
			se.printStackTrace();
		}
	}
}
