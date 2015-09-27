Start JBoss Servers as following:

Step-1). Start standalone_1 node
========
  ./standalone.sh -c standalone-ha.xml -Djboss.server.base.dir=../standalone_1 -Djboss.node.name=node1 -Djboss.socket.binding.port-offset=100

Step-2). Start standalone_2 node
========
./standalone.sh -c standalone-ha.xml -Djboss.server.base.dir=../standalone_2 -Djboss.node.name=node2 -Djboss.socket.binding.port-offset=200

Step-3). Make sure that you have added ApplicationRealm user by running the $JBOSS_HOME/bin/add-user.sh   script as following:
========
[jsenshar@localhost bin]$ ./add-user.sh -a ejbone password
Added user 'userone' to file '/home/jaysensharma/jbossas-7.1.3/standalone/configuration/application-users.properties'
Added user 'userone' to file '/home/jaysensharma/jbossas-7.1.3/domain/configuration/application-users.properties'


Step4). 
======= compile , deploy and run the program as following:

[jsenshar@localhost Clustered_EJB_With_CustomJNDI]$ ant
Buildfile: build.xml

build_ear:
   [delete] Deleting directory /home/jaysensharma/Clustered_EJB_With_CustomJNDI/tmp
    [mkdir] Created dir: /home/jaysensharma/Clustered_EJB_With_CustomJNDI/tmp
    [javac] Compiling 3 source files to /home/jaysensharma/Clustered_EJB_With_CustomJNDI/tmp
      [jar] Building jar: /home/jaysensharma/Clustered_EJB_With_CustomJNDI/tmp/remoteEJB.jar

deploy:
     [echo] *******************  Deploying the EJB JAR file remoteEJB.jar *********************
     [echo] ********** build/remoteEJB.jar to /home/jaysensharma/jbossas-7.1.3/standalone_1/deployments **********
     [echo] ********** build/remoteEJB.jar to /home/jaysensharma/jbossas-7.1.3/standalone_2/deployments **********
     [copy] Copying 1 file to /home/jaysensharma/jbossas-7.1.3/standalone_1/deployments
     [copy] Copying 1 file to /home/jaysensharma/jbossas-7.1.3/standalone_2/deployments
     [echo] *******************  Deployed Successfully   *********************

all:

BUILD SUCCESSFUL
Total time: 2 seconds



=================

[jsenshar@localhost Clustered_EJB_With_CustomJNDI]$ ant run
Buildfile: build.xml

run:
   [delete] Deleting directory /home/jaysensharma/Clustered_EJB_With_CustomJNDI/tmp
    [mkdir] Created dir: /home/jaysensharma/Clustered_EJB_With_CustomJNDI/tmp
    [javac] Compiling 2 source files to /home/jaysensharma/Clustered_EJB_With_CustomJNDI/tmp
      [jar] Building jar: /home/jaysensharma/Clustered_EJB_With_CustomJNDI/build/remoteEJBClient.jar
   [delete] Deleting directory /home/jaysensharma/Clustered_EJB_With_CustomJNDI/tmp
     [java] Apr 24, 2013 3:33:05 PM org.xnio.Xnio <clinit>
     [java] INFO: XNIO Version 3.0.7.GA-redhat-1
     [java] Apr 24, 2013 3:33:05 PM org.xnio.nio.NioXnio <clinit>
     [java] INFO: XNIO NIO Implementation Version 3.0.7.GA-redhat-1
     [java] Apr 24, 2013 3:33:05 PM org.jboss.remoting3.EndpointImpl <clinit>
     [java] INFO: JBoss Remoting version 3.2.14.GA-redhat-1
     [java] Apr 24, 2013 3:33:05 PM org.jboss.ejb.client.remoting.VersionReceiver handleMessage
     [java] INFO: EJBCLIENT000017: Received server version 1 and marshalling strategies [river]
     [java] Apr 24, 2013 3:33:05 PM org.jboss.ejb.client.remoting.RemotingConnectionEJBReceiver associate
     [java] INFO: EJBCLIENT000013: Successful version handshake completed for receiver context EJBReceiverContext{clientContext=org.jboss.ejb.client.EJBClientContext@496614e7, receiver=Remoting connection EJB receiver [connection=Remoting connection <6e79839>,channel=jboss.ejb,nodename=node1]} on channel Channel ID a75a7b86 (outbound) of Remoting connection 6833f0de to localhost/127.0.0.1:4547
     [java] Apr 24, 2013 3:33:06 PM org.jboss.ejb.client.remoting.VersionReceiver handleMessage
     [java] INFO: EJBCLIENT000017: Received server version 1 and marshalling strategies [river]
     [java] Apr 24, 2013 3:33:06 PM org.jboss.ejb.client.remoting.VersionReceiver handleMessage
     [java] INFO: EJBCLIENT000017: Received server version 1 and marshalling strategies [river]
     [java] Apr 24, 2013 3:33:06 PM org.jboss.ejb.client.remoting.RemotingConnectionEJBReceiver associate
     [java] INFO: EJBCLIENT000013: Successful version handshake completed for receiver context EJBReceiverContext{clientContext=org.jboss.ejb.client.EJBClientContext@496614e7, receiver=Remoting connection EJB receiver [connection=Remoting connection <4963f7a1>,channel=jboss.ejb,nodename=node2]} on channel Channel ID efe80dc0 (outbound) of Remoting connection 6aba4211 to localhost/127.0.0.1:4647
     [java] Apr 24, 2013 3:33:06 PM org.jboss.ejb.client.remoting.RemotingConnectionEJBReceiver associate
     [java] INFO: EJBCLIENT000013: Successful version handshake completed for receiver context EJBReceiverContext{clientContext=org.jboss.ejb.client.EJBClientContext@496614e7, receiver=Remoting connection EJB receiver [connection=Remoting connection <bb273cc>,channel=jboss.ejb,nodename=node2]} on channel Channel ID e145591a (outbound) of Remoting connection 4d8ce14a to /127.0.0.1:4647
     [java] Apr 24, 2013 3:33:06 PM org.jboss.ejb.client.EJBClient <clinit>
     [java] INFO: JBoss EJB Client version 1.0.11.Final-redhat-1
     [java] 
     [java] 	 remote.testMethod(i) = [CallerBean] returned Hello Test-0
     [java] 
     [java] 	 remote.testMethod(i) = [CallerBean] returned Hello Test-1
     [java] 
     [java] 	 remote.testMethod(i) = [CallerBean] returned Hello Test-2
     [java] 
     [java] 	 remote.testMethod(i) = [CallerBean] returned Hello Test-3
     [java] 
     [java] 	 remote.testMethod(i) = [CallerBean] returned Hello Test-4
     [java] 
     [java] 	 remote.testMethod(i) = [CallerBean] returned Hello Test-5

