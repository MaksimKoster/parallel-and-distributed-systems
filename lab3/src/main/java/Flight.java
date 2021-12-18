import scala.Serializable;
import scala.Tuple2;

public class Flight implements Serializable {
    public int initialAirportID;
    public int destAirportID;
    public float delaytime;
    public boolean cancelled;

    public static int INITIAL_AIRPORT_ID_INDEX = 11;
    public static int DEST_AIRPORT_ID_INDEX = 14;
    public static int DELAY_INDEX = 17;
    public static int CANCELLED_INDEX = 19;

    public Flight(int initialAirportID, int destAirportID, float delaytime, boolean cancelled){
        this.cancelled = cancelled;
        this.delaytime = delaytime;
        this.destAirportID = destAirportID;
        this.initialAirportID = initialAirportID;
    }

    public Tuple2<Tuple2<Integer, Integer>, Flight> getTupleWithAirports(){
        return new Tuple2<>(new Tuple2<>(initialAirportID, destAirportID), this);
    }

    public static Flight parseCSV(String csv){
        String[] list = csv.replaceAll("\"","").split(",");
        return new Flight(
                Integer.parseInt(list[INITIAL_AIRPORT_ID_INDEX]),
                Integer.parseInt(list[DEST_AIRPORT_ID_INDEX]),
                list[DELAY_INDEX].isEmpty() ? 0: Float.parseFloat(list[DELAY_INDEX]),
                list[CANCELLED_INDEX].isEmpty()
        );
    }
}
