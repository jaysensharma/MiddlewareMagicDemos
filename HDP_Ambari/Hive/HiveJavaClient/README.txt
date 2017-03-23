This is a simple Hive JDBC client.

How to run it ?
----------------

# cd HiveJavaClient
# mvn clean install exec:java


Output
------
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
