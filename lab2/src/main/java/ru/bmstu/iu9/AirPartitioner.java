package ru.bmstu.iu9;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AirPartitioner extends Partitioner<AirWritableComparable, Text>{
    @Override
    public int getPartition(AirWritableComparable key, Text value, int numReduceTasks){
        return key.getAirportID() % numReduceTasks;
    }
}
