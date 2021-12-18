mvn package
hadoop fs -rm -f -r output
hadoop fs -copyFromLocal 664600583_T_ONTIME_sample.csv
hadoop fs -copyFromLocal L_AIRPORT_ID.csv
spark-submit --class App --master yarn-client --num-executors 3 ./target/spark-examples-1.0-SNAPSHOT.jar
hadoop fs -copyToLocal output