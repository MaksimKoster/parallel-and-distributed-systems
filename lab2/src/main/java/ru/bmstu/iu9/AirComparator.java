package ru.bmstu.iu9;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class AirComparator extends WritableComparable{
    protected AirComparator(){
        super(AirWritableComparable.class, true);
    }

    @Override
    public int compare(WritableComparable air1, WritableComparable air2){
        AirWritableComparable airport1 = (AirWritableComparable) air1;
        AirWritableComparable airport2 = (AirWritableComparable) air2;

        return Integer.compare(airport1.getAirportID(),airport2.getAirportID());
    }

}
