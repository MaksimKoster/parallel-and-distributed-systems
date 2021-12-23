mvn package
hadoop fs -rm -f -r output;
export HADOOP_CLASSPATH=./target/hadoop-examples-1.0-SNAPSHOT.jar
hadoop fs -copyFromLocal 664600583_T_ONTIME_sample.csv
hadoop fs -copyFromLocal L_AIRPORT_ID.csv
hadoop ru.bmstu.iu9.AirApp 664600583_T_ONTIME_sample.csv L_AIRPORT_ID.csv output
rm -rf output
hadoop fs -copyToLocal output