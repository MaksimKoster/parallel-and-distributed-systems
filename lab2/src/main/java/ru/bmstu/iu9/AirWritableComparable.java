package ru.bmstu.iu9;

import java.io.*;
import java.util.Objects;

import org.apache.hadoop.io.WritableComparable;

public class AirWritableComparable implements WritableComparable{
    private int airportID;
    private int indicator;

    public AirWritableComparable(){}

    public void setAirportID(int airportID){
        this.airportID = airportID;
    }

    public void setIndicator(int indicator){
        this.indicator = indicator;
    }

    public int getAirportID(){
        return airportID;
    }

    public int getIndicator(){
        return indicator;
    }

    public AirWritableComparable(int airportID, int indicator){
        this.airportID = airportID;
        this.indicator = indicator;
    }

    @Override
    public void readFields(DataInput data) throws IOException{
        this.airportID = data.readInt();
        this.indicator = data.readInt();
    }

    @Override
    public void write(DataOutput data) throws IOException{
        data.writeInt(airportID);
        data.writeInt(indicator);
    }

    @Override
    public String toString(){
        return "AirWritableComparable{" +
                "airportID=" + airportID +
                ", indicator=" + indicator + "}";
    }

    @Override
    public int compareTo(Object t){
        AirWritableComparable air2 = (AirWritableComparable)t;
        if (this.airportID > air2.airportID) return 1;
        if (this.airportID < air2.airportID) return -1;
        return Integer.compare(this.indicator, air2.indicator);
    }

    @Override
    public boolean equals(Object t){
        if(this == t) return true;
        if (t == null || getClass() != t.getClass()) return false;
        AirWritableComparable air2 = (AirWritableComparable)t;
        return (airportID == air2.airportID) && (indicator == air2.indicator);
    }

    @Override
    public int hashcode(){
        return Objects.hash(airportID, indicator);
    }

}
