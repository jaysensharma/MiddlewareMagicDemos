#!/bin/sh
echo "Exporting JBOSS_HOME...."
export JBOSS_HOME=/Ravish_bk/installers/jboss-as-7.1.2.Final   #<---- Change as per your environment
echo "JBOSS_HOME Exported....!!!"
echo ""
echo "Exporting JAVA_HOME...."
export JAVA_HOME=/home/rmody/JBoss_Data/Jdk/jdk1.6.0_21   #<---- Change as per your environment
echo "JBOSS_HOME Exported....!!!"
echo ""
echo "Exporting CLASSPATH...."
export CLASSPATH=$JBOSS_HOME/bin/client/jboss-client.jar:$CLASSPATH:.:
echo "CLASSPATH Exported....!!!"
echo ""
echo "Exporting PATH...."
export PATH=$JAVA_HOME/bin:$PATH
echo "PATH Exported....!!!"
echo ""
echo "========================================================================"
echo "JBOSS_HOME = " $JBOSS_HOME
echo ""
echo "JAVA_HOME = " $JAVA_HOME
echo ""
echo "CLASSPATH = " $CLASSPATH
echo ""
echo "PATH = " $PATH
echo "========================================================================"

