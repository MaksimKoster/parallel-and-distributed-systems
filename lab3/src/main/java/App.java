import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple2;

import java.util.Map;
import java.util.Objects;

public class App {
    static String APP_NAME = "lab3";
    static String INPUT_AIRPORT = "L_AIRPORT_ID.csv";
    static String INPUT_FLIGHT = "664600583_T_ONTIME_sample.csv";
    static String OUTPUT = "output";

    public static void main(String[] args){
        SparkConf conf = new SparkConf().setAppName(APP_NAME);
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> dataAirports = sc.textFile(INPUT_AIRPORT);
        String firstAirport = dataAirports.first();

        final Broadcast<Map<Integer, String>> airportCast = sc.broadcast(
                dataAirports.filter(Airport -> !Objects.equals(firstAirport, Airport))
                        .mapToPair(dataAirport -> Airport.parseCSV(dataAirport).getTuple())
                        .collectAsMap()
        );

        JavaRDD<String> dataFlights = sc.textFile(INPUT_FLIGHT);
        String firstFlight = dataFlights.first();

        dataFlights.filter(flight -> !Objects.equals(firstFlight, flight))
                .mapToPair(flight ->Flight.parseCSV(flight).getTupleWithAirports())
                .combineByKey(
                        FlightReduce::new,
                        FlightReduce::storedata,
                        FlightReduce::merge
                )
                .mapToPair(statistics -> new Tuple2<>(
                        statistics._2.getStatistic(),
                        "From:" + airportCast.value().get(statistics._1._1) +
                                "To:" + airportCast.value().get(statistics._1._2)
                ))
                .saveAsTextFile(OUTPUT);

    }

}
