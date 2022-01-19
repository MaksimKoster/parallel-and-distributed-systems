import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.stream.ActorMaterializer;
import org.apache.log4j.BasicConfigurator;

public class AnonymizeApp {
    public static void main(String[] args){
        if (args.length < 2){
            System.err.println("No port argument");
            System.exit(-1);
        }
        BasicConfigurator.configure();
        ActorSystem system = ActorSystem.create("lab6");
        ActorRef actConf = system.actorOf(Props.create(ActorSys.ActorConf.class));
        final ActorMaterializer materializer = ActorMaterializer.create(system);
    }
}
