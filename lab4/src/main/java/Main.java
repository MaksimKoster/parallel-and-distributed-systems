import akka.actor.*;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException{
        ActorSystem system = ActorSystem.create("routes");
        ActorRef storeActor = system.actorOf(Props.create(StoreActor.class));
        final SupervisorStrategy strategy = new OneForOneStrategy(
                5,
                Duration.ofMinutes(1),
                Collections.<Class <? extends  Throwable>> singletonList(Exception.class));
        ActorRef router = system.actorOf();
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        MainHttp instance = new MainHttp(system, storeActor, router);
        
        )
    }
}
