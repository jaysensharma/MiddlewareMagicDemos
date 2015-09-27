<h3> Replacement of SAR dependency feature of JBossAS5 </h3>
 In JBoss AS7 the SAR <depends> tag does not work as expected so if you want your Service to be dependent on some other services like DataSource then you should create @Singleton Bean using @Startup annotation.

<h4>How to deploy "mysql-connector-java-5.1.13-bin.jar" as a deployable in JBoss AS7 and create DataSource.</h4>

./jboss-cli.sh -c --controller=10.10.10.10:9999 --file=/home/jaysensharma/SingletonServiceDemo/createDataSource.cli 


