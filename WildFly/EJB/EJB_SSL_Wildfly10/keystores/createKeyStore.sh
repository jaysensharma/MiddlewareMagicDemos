########################################
# Creating Server and Client KeyStores.
########################################
# NOTE: "keytool" is a utility that can be found inside the "$JAVA_HOME/bin" so make sire the PATH includes the JDK's bin directory.

WILDFLY_KEYSTORE="middlewaremagic_server.keystore"
CLIENT_TRUSTSTORE="clientTrustStore.keystore"

WILDFLY_STORE_PWD="middleware+magic"
CLIENT_STORE_PWD="middleware+magic+client"

WILDFLY_EXPORTED_CERTIFICATE="middlewaremagic_server.cer"

WILDFLY_STORE_ALIAS="middlewaremagic_server"
CLIENT_STORE_ALIAS="clientalias"

echo ""
echo ""
echo "Creating WildFly Server side Keystore. (${WILDFLY_KEYSTORE})"
echo "----------------------------------------"
keytool -genkey -v -alias ${WILDFLY_STORE_ALIAS} -keyalg RSA -keysize 1024 -keystore ${WILDFLY_KEYSTORE} -validity 3650 -keypass ${WILDFLY_STORE_PWD} -storepass ${WILDFLY_STORE_PWD} -dname "CN=127.0.0.1, OU=MiddlewareMagic, O=Blog, L=Bangalore, S=Karnataka, C=IN"


echo ""
echo ""
echo "Exporting the key (${WILDFLY_EXPORTED_CERTIFICATE}) from the  WildFly ServerSide keystore."
echo "----------------------------------------"
keytool -export -keystore ${WILDFLY_KEYSTORE} -alias ${WILDFLY_STORE_ALIAS} -file ${WILDFLY_EXPORTED_CERTIFICATE} -keypass ${WILDFLY_STORE_PWD} -storepass ${WILDFLY_STORE_PWD}


echo ""
echo ""
echo "Creating Client side Keystore/truststore. (${CLIENT_TRUSTSTORE})"
echo "----------------------------------------"
keytool -genkey -v -alias ${CLIENT_STORE_ALIAS} -keyalg RSA -keysize 1024 -keystore ${CLIENT_TRUSTSTORE} -validity 3650 -keypass ${CLIENT_STORE_PWD} -storepass ${CLIENT_STORE_PWD} -dname "CN=127.0.0.1, OU=MiddlewareMagic, O=Blog, L=Bangalore, S=Karnataka, C=IN"


echo ""
echo ""
echo "Importing the WildFLy Servers key ${WILDFLY_EXPORTED_CERTIFICATE} to the Client's truststore ${CLIENT_TRUSTSTORE}."
echo "----------------------------------------"
keytool -import -v -trustcacerts -alias ${WILDFLY_STORE_ALIAS} -file ${WILDFLY_EXPORTED_CERTIFICATE} -keystore ${CLIENT_TRUSTSTORE} -keypass ${CLIENT_STORE_PWD} -storepass ${CLIENT_STORE_PWD}


echo "Certificates created Successfully !!!"
