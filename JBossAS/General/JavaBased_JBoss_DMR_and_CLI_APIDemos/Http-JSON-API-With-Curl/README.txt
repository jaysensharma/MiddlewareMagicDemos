Refer to following link to get more information on this:

http://middlewaremagic.com/jboss/?p=2488
AND
http://middlewaremagic.com/jboss/?p=2476




Run the following curl commands to send POST Data to your JBoss AS 7.1.2 or above to perform various configuration & monitoring operations:

Wget:
======
wget -O out.txt "http://admin:admin123@localhost:9990/management/subsystem/logging/console-handler/CONSOLE?operation=attribute&name=level"


Curl
======

App Static Details
-------------------
curl --digest http://admin:admin123@localhost:9990/management --header "Content-Type: application/json"  -d '{"operation":"read-resource", "address":[{"deployment":"myapp.war"},{"subsystem":"web"}], "json.pretty":1}' 


App Runtime details
-------------------
curl --digest http://admin:admin123@localhost:9990/management --header "Content-Type: application/json" -d '{"operation":"read-resource" , "include-runtime":"true" , "address":[{"deployment":"myapp.war"},{"subsystem":"web"}], "json.pretty":1}' 


Checking Server's Current State
---------------------------------------
 curl --digest http://admin:admin123@localhost:9990/management --header "Content-Type: application/json" -d '{"operation":"read-attribute","name":"server-state","json.pretty":1}'


Resetting the “max-pool-size” of ExampleDS
----------------------------------------
curl --digest http://admin:admin123@localhost:9990/management --header "Content-Type: application/json" -d '{"operation":"write-attribute","address":[{"subsystem":"datasources"},{"data-source":"ExampleDS"}],"name":"max-pool-size","value":10,"json.pretty":1}' 

Testing ExampleDS datasource connections
-----------------------------------------
 curl --digest http://admin:admin123@localhost:9990/management --header "Content-Type: application/json" -d '{"operation":"test-connection-in-pool","address":[{"subsystem":"datasources"},{"data-source":"ExampleDS"}],"json.pretty":1}'


Shutting down JBoss Standalone Server
-----------------------------------------
curl --digest http://admin:admin123@localhost:9990/management --header "Content-Type: application/json"  -d '{"opeation":"shutdown" , "json.pretty":1}'

Changing console-handler "CONSOLE" logging level to "WARN"
------------------------------------------------------------
 curl --digest http://admin:admin123@localhost:9990/management --header "Content-Type: application/json" -d '{"operation":"write-attribute","address":[{"subsystem":"logging"},{"console-handler":"CONSOLE"}],"name":"level","value":"WARN", "json.pretty":1}'


Getting Server Environment Details
-------------------------------------
curl --digest http://admin:admin123@localhost:9990/management --header "Content-Type: application/json" -d '{"operation":"read-resource", "include-runtime":"true", "address":[{"core-service":"server-environment"}], "json.pretty":1}'


Getting JVM Memory & Runtime Details
------------------------------------
curl --digest http://admin:admin123@localhost:9990/management --header "Content-Type: application/json" -d '{"operation":"read-resource", "include-runtime":"true", "address":[{"core-service":"platform-mbean"}, {"type":"memory"}], "json.pretty":1}'
