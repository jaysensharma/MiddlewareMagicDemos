#!/bin/sh
# Takes the JavaApp PID as an argument.
# Make sure you set JAVA_HOME
# Create thread dumps a specified number of times (i.e. LOOP) and INTERVAL.
# Thread dumps will be collected in the file "jcmd_threaddump.out", in the same directory from where this script is been executed.
#   Usage:
#          sudo  -  $user_Who_Owns_The_JavaProcess
#          ./threaddump_cpu_with_cmd.sh <JAVA_APP_PID>
#
#
#   Example:
#   NameNode PID is "5752" and it is started by user "hdfs" then run this utility as following:
#
#   su -l hdfs  -c "/tmp/threaddump_cpu_with_cmd.sh  5752"
################################################################################################


# Number of times to collect data. Means total number of thread dumps.
LOOP=10

# Interval in seconds between data points.
INTERVAL=10

# Where to generate the threddump & top output files.
WHERE_TO_GENERATE_OUTPUT_FILES="/tmp"

# Setting the Java Home, by giving the path where your JDK is kept
# USERS MUST SET THE JAVA_HOME before running this scripta s following:
JAVA_HOME=/usr/jdk64/jdk1.8.0_60

echo "Writing CPU data log files to Directory:  $WHERE_TO_GENERATE_OUTPUT_FILES"

for ((i=1; i <= $LOOP; i++))
  do
    #$JAVA_HOME/bin/jstack -l $1 >> jstack_threaddump.out
    $JAVA_HOME/bin/jcmd  $1  Thread.print >> $WHERE_TO_GENERATE_OUTPUT_FILES/jcmd_threaddump.out
    _now=$(date)
    echo "${_now}" >> $WHERE_TO_GENERATE_OUTPUT_FILES/top_highcpu.out
    top -b -n 1 -H -p $1 >> $WHERE_TO_GENERATE_OUTPUT_FILES/top_highcpu.out
    echo "Collected 'top' output and Thread Dump #" $i
    if [ $i -lt $LOOP ]; then
        echo "Sleeping for $INTERVAL seconds."
        sleep $INTERVAL
    fi
done


