package ru.bmstu.iu9;

import javafx.util.Pair;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class AirMapper extends Mapper<LongWritable, Text, AirWritableComparable, Text>{
    public static int AIRPORT_INDICATOR = 0;
    public static int ID_INDEX = 0;
    public static int NAME_INDEX = 1;
    public static String SPLIT = "\",";

    protected Pair<Integer,String> SplitDataToIdAirname(Text value, String reg){
        Pair<Integer, String> ans;
        String[] params = value.toString().split(reg);
        int airID = Integer.parseInt(params[ID_INDEX].replaceAll("\"",""));
        String airName = params[NAME_INDEX];
        ans = new Pair<>(airID, airName);
        return ans;
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{

        if (key.get() > 0){
            Pair<Integer,String>  parsed = SplitDataToIdAirname(value, SPLIT);
            context.write(new AirWritableComparable(parsed.getKey(), AIRPORT_INDICATOR), new Text(parsed.getValue()));
        }
    }
}
