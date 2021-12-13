package ru.bmstu.iu9;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirCompartor extends WritableComparable{
    protected AirCompartor(){
        super(AirWritableComparable.class, true);
    }

    @Override

}
