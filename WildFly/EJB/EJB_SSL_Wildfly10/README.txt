
This demo shows how to use SSL using "https-remoting" protocol to invoke an EJB Deployed on WildFly 10.

Steps to configure the required setup on WildLFy10:


1). Creating SSL based "HttpsRealm"

/core-service=management/security-realm=HttpsRealm/:add
/core-service=management/security-realm=HttpsRealm/server-identity=ssl:add(keystore-path="middlewaremagic_server.keystore", keystore-password="middleware+magic", key-password="middleware+magic", alias="middlewaremagic_server", keystore-relative-to=jboss.server.config.dir)
/core-service=management/security-realm=HttpsRealm/authentication=properties:add(path=httpsrealm-users.properties, relative-to=jboss.server.config.dir, plain-text=false)
:reload

2). Configuring Undertow to add https connector and setting up the "remoting" and "ejb3" subsystems.
/subsystem=undertow/server=default-server/https-listener=default-https/:add(security-realm=HttpsRealm,socket-binding=https,enabled-protocols=TLSv1.2)
/subsystem=remoting/http-connector=https-remoting-connector/:add(connector-ref=default-https,sasl-protocol=remote,security-realm=HttpsRealm)
/subsystem=ejb3/service=remote/:write-attribute(name=connector-ref,value=https-remoting-connector)
:reload


For more details: 

See:

##EJB3 over SSL in WildFly 10 automation using Apache Maven plugins.
http://middlewaremagic.com/jboss/?p=2783