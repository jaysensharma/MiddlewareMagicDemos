//  ## JDBC Based JavaScript  ##

var getConnection = function() {
    var Properties = Java.type("java.util.Properties");
    var Driver = Java.type("com.mysql.jdbc.Driver");
    var driver = new Driver();
    var properties = new Properties();
    var conn = null;
    try {
        properties.setProperty("user", "root");
        properties.setProperty("password", "testpwd");
        conn = driver.connect("jdbc:mysql://localhost:3307/TestJavaScriptDB", properties);
        return conn;
    } finally {
    
    }
}

print("****** conn : " + getConnection());

function runQuery(conA, query) {
   try {
       var stmt = conA.prepareStatement(query);
       var resultSet = stmt.executeQuery();
       print("      --------------------------- ");
       while (resultSet.next()) {
          print("\t" + resultSet.getString("custId") + " - "+ resultSet.getString("custName"))
       }
       print("      --------------------------- ");
       
    } finally {
      if (resultSet)
         try {
            resultSet.close();
            print("\nResultSet Closed.");
         }
         catch(e) {}
      if (stmt)
         try {
            stmt.close();
            print("Statement Closed.");            
         } catch (e) { print( e );  }
      if (conA)        
         try { 
            conA.close();
            print( "Connection Closed." );
         } catch (e) { print( e );  }          
      }  
 } 

var con1 = getConnection();
runQuery(con1, "select * from CUSTOMER");






/*
$JAVA_HOME/bin/jjs testDB.js 

Exception in thread "main" java.lang.RuntimeException: java.lang.ClassNotFoundException: com.mysql.jdbc.Driver
	at jdk.nashorn.internal.runtime.ScriptRuntime.apply(ScriptRuntime.java:397)
	at jdk.nashorn.tools.Shell.apply(Shell.java:397)
	at jdk.nashorn.tools.Shell.runScripts(Shell.java:326)
	at jdk.nashorn.tools.Shell.run(Shell.java:172)
	at jdk.nashorn.tools.Shell.main(Shell.java:136)
	at jdk.nashorn.tools.Shell.main(Shell.java:112)
Caused by: java.lang.ClassNotFoundException: com.mysql.jdbc.Driver
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at jdk.nashorn.internal.runtime.Context.findClass(Context.java:1051)
	at jdk.nashorn.internal.objects.NativeJava.simpleType(NativeJava.java:493)
	at jdk.nashorn.internal.objects.NativeJava.type(NativeJava.java:322)
	at jdk.nashorn.internal.objects.NativeJava.type(NativeJava.java:314)
	at jdk.nashorn.internal.objects.NativeJava.type(NativeJava.java:310)
	at jdk.nashorn.internal.scripts.Script$Recompilation$1$64$testDB.testDatabase(testDB.js:5)
	at jdk.nashorn.internal.scripts.Script$testDB.:program(testDB.js:26)
	at jdk.nashorn.internal.runtime.ScriptFunctionData.invoke(ScriptFunctionData.java:640)
	at jdk.nashorn.internal.runtime.ScriptFunction.invoke(ScriptFunction.java:228)
	at jdk.nashorn.internal.runtime.ScriptRuntime.apply(ScriptRuntime.java:393)
	... 5 more


So run the jjs by specifying the CLASSPATH as following:

$JAVA_HOME/bin/jjs -cp /PATH/TO/mysql-connector-java-5.1.37-bin.jar  testDB.js 
*/
