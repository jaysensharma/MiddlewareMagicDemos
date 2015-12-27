########################################
# Creating Server and Client KeyStores.
########################################
# NOTE: "keytool" is a utility that can be found inside the "$JAVA_HOME/bin" so make sire the PATH includes the JDK's bin directory.

mkdir Keystores
cd Keystores/

echo ""
echo ""
echo "Creating WLS Server side Keystore. (wls12c.keystore)"
echo "----------------------------------------"
keytool -genkey -v -alias wlsalias -keyalg RSA -keysize 1024 -keystore wls12c.keystore -validity 3650 -keypass middleware+magic -storepass middleware+magic -dname "CN=127.0.0.1, OU=MiddlewareMagic, O=Blog, L=Bangalore, S=Karnataka, C=IN"


echo ""
echo ""
echo "Exporting public key (wls12c_server.cer) from the  WLS ServerSide keystore."
echo "----------------------------------------"
keytool -export -keystore wls12c.keystore -alias wlsalias -file wls12c_server.cer -keypass middleware+magic -storepass middleware+magic


echo ""
echo ""
echo "Creating Client side Keystore/truststore. (clientTrustStore.keystore)"
echo "----------------------------------------"
keytool -genkey -v -alias clientalias -keyalg RSA -keysize 1024 -keystore clientTrustStore.keystore -validity 3650 -keypass middleware+magic+client -storepass middleware+magic+client -dname "CN=127.0.0.1, OU=MiddlewareMagic, O=Blog, L=Bangalore, S=Karnataka, C=IN"


echo ""
echo ""
echo "Importing the WLS Servers public key to the Client's truststore."
echo "----------------------------------------"
keytool -import -v -trustcacerts -alias wlsalias -file wls12c_server.cer -keystore clientTrustStore.keystore -keypass middleware+magic+client -storepass middleware+magic+client


echo "Certificates created Successfully !!!"