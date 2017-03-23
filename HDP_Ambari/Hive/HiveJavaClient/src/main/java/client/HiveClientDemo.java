package client;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HiveClientDemo {

	private static String commonDriverName = "org.apache.hive.jdbc.HiveDriver";
	private static String url = "jdbc:hive2://erie3.example.com:2181,erie1.example.com:2181,erie4.example.com:2181,erie2.example.com:2181/;serviceDiscoveryMode=zooKeeper;zooKeeperNamespace=hiveserver2";
	private static String userName = "hive";
	private static String userPass = "hive";
	public static void main(String[] args) throws Exception {
		Class.forName(commonDriverName);
		Connection con = DriverManager.getConnection(url, userName, userPass);
		System.out.println("\n\t Got Connection: " + con);

		System.out.println("\n\t Listing 'default' Database tables of hive.");
		Statement stmt = con.createStatement();
    		String sql = "show tables";
    		System.out.println("Executing Query: " + sql);
    		ResultSet rs = stmt.executeQuery(sql);
    		while (rs.next()) {
      			System.out.println(rs.getString(1));
    		}
	}
}

/*
How to compile and run?

# mvn clean install exec:java

log4j:WARN No appenders could be found for logger (org.apache.hive.jdbc.Utils).
log4j:WARN Please initialize the log4j system properly.
log4j:WARN See http://logging.apache.org/log4j/1.2/faq.html#noconfig for more info.

	 Got Connection: org.apache.hive.jdbc.HiveConnection@5f070163

	 Listing 'default' Database tables of hive.

Executing Query: show tables
currency
currency1
currency2
firewall_logs
nyse_stocks
test
test1
test2
test4
test5
test6
test_x
xyz
*/


