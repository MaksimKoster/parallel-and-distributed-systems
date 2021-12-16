import org.apache.spark.SparkConf;

public class App {
    static String APP_NAME = "lab3";
    static String INPUT_AIRPORT = "L_AIRPORT_ID.csv";
    static String INPUT_FLIGHT = "664600583_T_ONTIME_sample.csv";
    static String OUTPUT = "output";

    public static void main(String[] args){
        SparkConf conf = SparkCond().setAppname(APP_NAME);
    }

}
