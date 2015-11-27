//  ## JDBC Based JavaScript  ##

$undertow
    .onGet("/getCustomerRecords",
        {headers: {"content-type": "text/plain"}},
        [function ($exchange) {
            var con1 = getConnection();
            var customerData = runQuery(con1, "select * from CUSTOMER");
            return "Got Customer Data: " + customerData;
        }])
        
     
     
//  Java Script Functions  //        
var getConnection = function() {
    var Properties = Java.type("java.util.Properties");
    var Driver = Java.type("com.mysql.jdbc.Driver");
    var driver = new Driver();
    var properties = new Properties();
    var conn = null;
    try {
        properties.setProperty("user", "root");
        properties.setProperty("password", "testpwd");
        conn = driver.connect("jdbc:mysql://localhost:3306/TestJavaScriptDB", properties);
        print("****** Got the JDBC Connection : " +conn);
        return conn;
    } finally {
    }
}

// Declaring runQuery function
var runQuery = function(conA, query) {
   var result = "";
   try {
       var stmt = conA.prepareStatement(query);
       var resultSet = stmt.executeQuery();
       print("      --------------------------- ");
       while (resultSet.next()) {
          result = result + " [";
          var rowData = resultSet.getString("custId") + " - " + resultSet.getString("custName");
          print("\t" + rowData);
          result = result + rowData + "], "; 
       }
       print("      --------------------------- ");
       return result;
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

//  Following Cals are commended else those wil be executed as soon as WildFly startes deploying this WebApplication.
//var con1 = getConnection();
//runQuery(con1, "select * from CUSTOMER");
