WildFly 8.x has become little bit old however it had bring some major changes in way we used to lookup resources in JBossAS7, hence as part of this article we will see what those changes were in the remoting based JNDI lookup approach.</p>

<p style="text-align: justify;">In this article we will see what all different issues we may face while looking up a JMS resource from a Standalone java client and what al things we should keep in mind while doing so. We will also see what all jars are needed at the client classpath. Also how to creation the InitialContext object and which protocol should we use like ”remoting” or “http-remoting” etc.</p>

<p style="text-align: justify;">In this example we will be learning the following things:</p>
1. How to create a simple JMS Queue on WildFly 8.x 
2. How to create a user in ApplicationRealm and assign it to “guest” role.
3. How to create the InitialContext on the standalone client side.
4. What all jars are needed on the client side in order to lookup the JNDI resources deployed on WildFly.
5. Some common issues which you might encounter while running t=such java code.


See article:

<b>Wildfly 8 based standalone JMS client code common issues and remedies ?</b>
http://middlewaremagic.com/jboss/?p=2724