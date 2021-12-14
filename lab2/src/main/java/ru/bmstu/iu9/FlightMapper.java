package ru.bmstu.iu9;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlightMapper extends Mapper<LongWritable, Text, AirWritableComparable, Text> {

    public static int FLIGHT_INDICATOR = 1;
    public static int AIRPORT_INDEX = 14;
    public static int DELAYTIME_INDEX = 18;
    public static String DELIMETR = ",";

    @Override
    protected void map(LongWritable key, Text value, Context constext ) throws IOException, InterruptedException {
        String[]params = value.toString().split(DELIMETR);
        if (key.get() > 0){
            String delay = params[DELAYTIME_INDEX];
            float delayFloat = delay.isEmpty() ? 0.0f : Float.parseFloat(delay);
            if (delayFloat > 0.0f){
                int airID = Integer.parseInt(params[AIRPORT_INDEX]);
                constext.write(new AirWritableComparable(airID, FLIGHT_INDICATOR), new Text(delay));
            }
        }
    }
}
