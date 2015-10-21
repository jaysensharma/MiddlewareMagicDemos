<p style="text-align: justify;">
As part of this article we will see how to configure a JMS Message bridge on WebLogic 12c side so that any message sent to the JMS Queue present on WebLogic will be automatically transferred to WildFly 8.2 Instance. The WebLogic Messaging Bridge is a forwarding mechanism that provides interoperability between WebLogic JMS implementations, and between JMS and other messaging products. We are going to use the Messaging Bridge to integrate WildFly 8.2 offered HornetQ messaging broker communication. A messaging bridge instance forwards messages between a pair of bridge source and target destinations. These destinations are mapped to a pair of bridge source and target destinations. The messaging bridge reads messages from the source bridge destination and forwards those messages to the target bridge destination.</p>

<p style="text-align: justify;">In order to know how to create a WebLogic Message bridge between WebLogic and JBoss 5 please refer to : http://middlewaremagic.com/weblogic/?p=6066</p>

<p style="text-align: justify;">As part of this article we will learn:</p>
1). How to create a WebLogic Message Bridge using WLST.
2). How to create a JMS Queue on WildFly 8.2 side.  (these steps will remain almost same for JBoss AS7 or JBoss EAP6, the only change will be in the Connection URL of WildFly or JBossAS. In WildFly we use "http-remoting://localhost:8080" where as in JBossAS7/EAP6 we use "remote://localhost:4447").
3). Then we will send some messages to WebLogic JMS Queues and will receive them from WildFly 8.2 JMS Queues. 



See: 
WebLogic 12c JMS Bridge to connect to WildFly 8.2 HornetQ Broker.
http://middlewaremagic.com/weblogic/?p=8227