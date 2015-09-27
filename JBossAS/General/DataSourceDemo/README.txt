1). Start JBoss Like following:
./standalone.sh -c standalone-full.xml 

2). Open the JBoss CLI utility to deploy MySQL Driver and create DataSource 

./jboss-cli.sh -c 

deploy /home/jaysensharma/DataSourceDemo/mysql-connector-java-5.1.13-bin.jar

/subsystem=datasources/data-source="MySqlDS":add(jndi-name="java:jboss/exported/MySqlDS",driver-name="mysql-connector-java-5.1.13-bin.jar",connection-url="jdbc:mysql://localhost:3306/testDB",user-name="root",password="redhat")


3).  This Demo creates a WAR file which shoud 3 ways of Looking up DataSource.
     1). Using Manual Lookup old style
     2). Lookup using <resource-ref>  name mentioned in the web.xml
     3). Injecting DataSource using @Resource.
