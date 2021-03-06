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

    private static Pair<Integer, String> SplitDataToCodeName(String data, String reg){
        String[] params = data.split(",", 2);
        String codeStr = params[CODE_INDEX].replaceAll(reg,"");
        String nameAir = params[NAME_INDEX];
        int codeAir = Integer.parseInt(codeStr);
        return new Pair<>(codeAir, nameAir);
    }

    public static Airport parseCSV(String csv){
        Pair<Integer, String> ans = SplitDataToCodeName(csv, Split);
        return new Airport(
                ans.getKey(),
                ans.getValue()
        );
    }

    public final Tuple2<Integer, String> getTuple(){
        return new Tuple2<>(code, name);
    }
}
