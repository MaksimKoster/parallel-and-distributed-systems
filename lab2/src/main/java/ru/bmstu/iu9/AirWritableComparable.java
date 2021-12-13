package ru.bmstu.iu9;

import java.io.*;
import java.util.Objects;

import org.apache.hadoop.io.WritableComparable;

public class AirWritableComparable implements WritableComparable{
    private int airportID;
    private int indicator;

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

    @override 
}
