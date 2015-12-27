#domainName=base_domain
adminURL="t3://localhost:7001"
adminUserName="weblogic"
adminPassword="weblogic1"

connect(adminUserName, adminPassword, adminURL)

edit()
startEdit()
cd('/Servers/AdminServer')
cmo.setKeyStores('CustomIdentityAndJavaStandardTrust')
cmo.setCustomIdentityKeyStoreFileName('/Users/jsensharma/NotBackedUp/MM_Tests/WLS/SSL_JMX/WLST_and_Keys/Keystores/wls12c.keystore')
cmo.setCustomIdentityKeyStoreType('jks')
cmo.setCustomIdentityKeyStorePassPhrase('middleware+magic')
activate()
startEdit()

startEdit()
cd('/Servers/AdminServer/SSL/AdminServer')
cmo.setServerPrivateKeyAlias('wlsalias')
cmo.setServerPrivateKeyPassPhrase('middleware+magic')
cmo.setEnabled(true)
cmo.setListenPort(7443)
activate()
