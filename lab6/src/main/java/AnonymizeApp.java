import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.apache.log4j.BasicConfigurator;

public class AnonymizeApp {

    public static void main(String[] args){
        if (args.length < 2){
            System.err.println("enter port as parameter");
            System.exit(1);
        }
        BasicConfigurator.configure();
        System.out.println("start anonymize app");
        ActorSystem sys = ActorSystem.create("lab6")
    }
}
