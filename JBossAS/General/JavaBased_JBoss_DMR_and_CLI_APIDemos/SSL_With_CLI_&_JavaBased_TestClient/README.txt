Generate the self signed certificate using the following command
**************

keytool -genkey -keystore chap8.keystore -storepass rmi+ssl -keypass rmi+ssl -keyalg RSA -alias chapter8 -validity 3650 -dname "cn=chapter8 example,ou=admin book,dc=jboss,dc=org"


CLI Commands
**************
/subsystem=web/connector=https/:add(socket-binding=https,scheme=https,protocol=HTTP/1.1,secure=true,enabled=true,enable-lookups=false)


/subsystem=web/connector=https/ssl=configuration:add(name="ssl",key-alias="chapter8",password="rmi+ssl",certificate-key-file="../standalone/configuration/chap8.keystore",protocol="TLSv1",verify-client="false",certificate-file="../standalone/configuration/chap8.keystore")


            <connector name="https" protocol="HTTP/1.1" scheme="https" socket-binding="https" secure="true">
                <ssl name="ssl" key-alias="chapter8" 
                     password="rmi+ssl" 
                     certificate-key-file="../standalone/configuration/chap8.keystore" 
                     protocol="TLSv1" verify-client="false" 
                     certificate-file="../standalone/configuration/chap8.keystore"/>
            </connector>


Deploy the Test WebApplication (test.war)
***************

NOTE:  Run the Client using :   

       java -Dhttps.protocols=TLSv1 -Djavax.net.debug=all TestHttpsClient
