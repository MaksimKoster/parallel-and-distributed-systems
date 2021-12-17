import scala.Serializable;

public class FlightReduce implements Serializable {
    public float maxDelay;
    public int countDelays;
    public int countCancelled;
    public int countFlights;

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
        return "maxdelay: " + maxDelay +
                " cancelled(%): " + (100 * ((float)countCancelled/(float)countFlights)) +
                " delayed(%): " + (100 * ((float)countDelays/(float)countFlights));
    }
}
