import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
public class App {
    static String APP_NAME = "lab3";
    static String INPUT_AIRPORT = "L_AIRPORT_ID.csv";
    static String INPUT_FLIGHT = "664600583_T_ONTIME_sample.csv";
    static String OUTPUT = "output";

    public static void main(String[] args){
        SparkConf conf = SparkCond().setAppname(APP_NAME);
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> dataAirports = sc.textFile(INPUT_AIRPORT);
        String firstDataAirport = dataAirports.first();
        
    }

}
