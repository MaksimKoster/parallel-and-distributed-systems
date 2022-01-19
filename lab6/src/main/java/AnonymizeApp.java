import akka.actor.ActorSystem;
import org.apache.log4j.BasicConfigurator;

public class AnonymizeApp {
    public static void main(String[] args){
        if (args.length < 2){
            System.err.println("No port argument");
            System.exit(-1);
        }
        BasicConfigurator.configure();
        ActorSystem system = ActorSystem.create("lab6");
    }
}
