#
# See the article: http://middlewaremagic.com/weblogic/?p=8294
# How to use WebLogic 12c provided Maven Synchronization Plug-In ?
#
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_79.jdk/Contents/Home
echo "JAVA_HOME = $JAVA_HOME"
echo 
export M2_HOME=/Users/jsensharma/NotBackedUp/Support_Tools/apache_maven_3.2.3
echo "M2_HOME = $M2_HOME"
echo
export M2_REPO=/Users/jsensharma/.m2/repository
echo "M2_REPO=$M2_REPO"
echo
export MW_HOME=/Users/jsensharma/NotBackedUp/Installed/wls1221
echo "MW_HOME = $MW_HOME"
echo
export PATH=$JAVA_HOME/bin:/Users/jsensharma/NotBackedUp/Support_Tools/apache_maven_3.2.3/bin:$PATH:
echo "PATH = $PATH"

cd $MW_HOME//wlserver/server/bin
. ./setWLSEnv.sh

cd $MW_HOME/oracle_common/plugins/maven/com/oracle/maven/oracle-maven-sync/12.2.1

mvn deploy:deploy-file -DpomFile=oracle-maven-sync-12.2.1.pom -Dfile=oracle-maven-sync-12.2.1.jar -Durl=file://${M2_REPO}

mvn help:describe -Dplugin=com.oracle.maven:oracle-maven-sync:12.2.1-0-0 -Ddetail  

mvn com.oracle.maven:oracle-maven-sync:12.2.1-0-0:push -Doracle-maven-sync.oracleHome=$MW_HOME -DtestingOnly=false

mvn archetype:crawl -Dcatalog=$HOME/.m2/archetype-catalog.xml