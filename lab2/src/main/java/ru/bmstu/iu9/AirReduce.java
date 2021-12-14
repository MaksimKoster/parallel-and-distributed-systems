package ru.bmstu.iu9;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class AirReduce extends Reducer<AirWritableComparable, Text, Text, Text> {
    @Override
    protected void reduce(AirWritableComparable key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
        Iterator<Text> valuesIter = values.iterator();
        String air = valuesIter.next().toString();
        float max, sum, count = 0.0f;
        float min = -1.0f;

        while(valuesIter.hasNext()){
            float delayTime = Float.parseFloat()
        }

    }
}
