import javafx.util.Pair;
import scala.Tuple2;
import scala.Serializable;

public class Airport implements Serializable {
    public int code;
    public String name;

    public static int CODE_INDEX = 0;
    public static int NAME_INDEX = 1;
    public static String Split = "\"";

    public Airport(int code, String name){
        this.code = code;
        this.name = name;
    }

    protected Pair<Integer,String> SplitDataToCodeName(String data, String reg){
        String[] params = data.split(",", 2);
        String code = params[CODE_INDEX].replaceAll(reg,"");
        String name = params[NAME_INDEX];

    }

    public static Airport parseCSV(String csv){
        String[] list = csv.split(",", 2);
        return new Airport(
                Integer.parseInt(list[CODE_INDEX].replaceAll("\"","")),
                list[NAME_INDEX]
        );
    }

    public final Tuple2<Integer, String> getTuple(){
        return new Tuple2<>(code, name);
    }
}
