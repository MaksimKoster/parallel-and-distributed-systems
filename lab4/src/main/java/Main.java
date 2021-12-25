import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.SupervisorStrategy;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{
        ActorSystem system = ActorSystem.create("routes");
        ActorRef storeActor = system.actorOf(Props.create(StoreActor.class));
        final SupervisorStrategy strategy
    }
}
