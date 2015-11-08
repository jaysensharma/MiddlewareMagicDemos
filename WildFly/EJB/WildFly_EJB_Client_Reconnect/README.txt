This example demonstrates why to use the EJB Client API and not the remote naming based API.

This example shows how the client can automatically gets reconnected to WildFly if the wildly instance goes down and rebooted again.

We are using the ÒEJB client APIÓ 


Users need to create a user in their WildFly as following:

$ ./add-user.sh -a ejbuser ejbuser@1
Updated user 'ejbuser' to file Ô/PATH/TO/wildfly-10.0.0.CR3-SNAPSHOT/standalone/configuration/application-users.properties'
Updated user 'ejbuser' to file Ô/PATH/TO/wildfly-10.0.0.CR3-SNAPSHOT/domain/configuration/application-users.properties'


##########################################
See the following article for more detail: 

Why to use WildFly based EJB Client API for Client auto reconnect logic ?
http://middlewaremagic.com/jboss/?p=2744