package ru.bmstu.iu9;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class AirMapper extends Mapper<LongWritable, Text, AirWritableComparable, Text>{
    public static int AIRPORT_INDCATOR = 0;
    public 
}
