import scala.Serializable;

public class FlightReduce implements Serializable {
    public float maxDelay;
    public int countDelays;
    public int countCancelled;
    public int countFlights;

    private static String FORMAT = "max:%3.0f,cancelled(%%):%6.2f,delay(%%):%6.2f";

    public FlightReduce(Flight f){
        maxDelay = f.delaytime;
        countDelays = f.delaytime == 0 ? 0:1;
        countCancelled = f.cancelled ? 0:1;
        countFlights = 1;
    }

    public static FlightReduce storedata(FlightReduce storage, Flight fl){
        storage.maxDelay = Math.max(storage.maxDelay, fl.delaytime);
        storage.countDelays += fl.delaytime == 0 ? 0:1;
        storage.countCancelled += fl.cancelled ? 0:1;
        storage.countFlights += 1;
        return storage;
    }

    public static FlightReduce merge(FlightReduce mainStorage, FlightReduce secondaryStorage){
        mainStorage.maxDelay = Math.max(mainStorage.maxDelay, secondaryStorage.maxDelay);
        mainStorage.countDelays += secondaryStorage.countDelays;
        mainStorage.countCancelled += secondaryStorage.countCancelled;
        mainStorage.countFlights += secondaryStorage.countFlights;
        return mainStorage;
    }

    public String getStatistic(){
        return String.format(
                FORMAT,
                maxDelay,
                100f * countCancelled/countFlights,
                100f * countDelays/countFlights
        );
    }
}
