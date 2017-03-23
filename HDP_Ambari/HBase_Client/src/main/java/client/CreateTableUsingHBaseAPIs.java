package client;
import java.io.IOException;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.conf.Configuration;

public class CreateTableUsingHBaseAPIs {
	public static void main(String[] args) throws IOException {
		// Instantiating configuration class
		Configuration config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.quorum", "erie3.example.com,erie1.example.com,erie4.example.com,erie2.example.com");
		config.set("hbase.zookeeper.property.clientPort", "2181");
		config.set("zookeeper.znode.parent", "/hbase-unsecure");
		config.addResource(new Path("/PATH/TO/HBase_Client/src/main/resources/hbase-site.xml"));
		config.addResource(new Path("/PATH/TO/HBase_Client/src/main/resources/core-site.xml"));
		config.addResource(new Path("/PATH/TO/HBase_Client/src/main/resources/hdfs-site.xml"));

		System.out.println("\n\t Got config: " + config);
		Connection conn = ConnectionFactory.createConnection(config);
		System.out.println("\n\t Got Connection: " + conn);
		Admin admin = conn.getAdmin();
		System.out.println("\n\t Got Admin: " + admin);

		HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("emp"));
		tableDescriptor.addFamily(new HColumnDescriptor("personal"));
		tableDescriptor.addFamily(new HColumnDescriptor("professional"));
		admin.createTable(tableDescriptor);
		System.out.println(" Table created ");
	}
}

/*
How to build and run?

# cd /PATH/TO/HBase_Client
# mvn clean install exec:run

*/

