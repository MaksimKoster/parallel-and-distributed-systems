package ru.bmstu.iu9;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.FileWriter;
import java.io.IOException;


public class AirMapper extends Mapper<LongWritable, Text, AirWritableComparable, Text>{
    public static int AIRPORT_INDICATOR = 0;
    public static int ID_INDEX = 0;
    public static int NAME_INDEX = 1;
    public static String REGEX = ",";

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
        String[] params = value.toString().split(REGEX);

        if (key.get() > 0){
            String airName;
            int airID;
            airID = Integer.parseInt(params[ID_INDEX]);
            airName = params[NAME_INDEX].split("\"")[0];
            context.write(new AirWritableComparable(airID, AIRPORT_INDICATOR), new Text(airName));
        }
    }
}
