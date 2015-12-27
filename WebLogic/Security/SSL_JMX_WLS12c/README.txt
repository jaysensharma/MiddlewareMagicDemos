See:
WebLogic12c with t3s secure protocol and the JMX client
http://middlewaremagic.com/weblogic/?p=8348

## What this demo is about ?

As part of this demo we will see how to use implement One way SSL on weblogic so that a Client can interact with it using https/t3s in a secure manner.

1. How to configure the “CustomIdentityAndJavaStandardTrust” on weblogic, In a complete automated way using WLST scripting.

2. How to configure the SSL port on WebLogic 12.2.1 using WLST.

3. How to create Server side Keystore and Client side truststore.

4. Running a simple MBean Client which will use “t3s” protocol to access/query the MBeans which are present on WLS server.

5. Troubleshooting some very common issues which users might encounter while implementing SSL on WebLogic 12c.