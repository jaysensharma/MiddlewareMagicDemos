Make sure that you start the JBossAS 7.1.2 with the following system property:

./standalone.sh -c standalone-full.xml  -Dorg.jboss.as.logging.per-deployment=false


JIRA reference:
[1]  https://issues.jboss.org/browse/AS7-514
