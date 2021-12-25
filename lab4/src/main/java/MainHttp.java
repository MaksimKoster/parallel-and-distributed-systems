import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.server.Route;

import java.time.Duration;

import static akka.http.javadsl.server.Directives.*;

public class MainHttp {
    static final Duration TIMEOUT = Duration.ofSeconds(5);
    public static final String TEST_EXEC = "test execution started";

    ActorSystem system;
    ActorRef storeActor;
    ActorRef routeActor;

    MainHttp(ActorSystem system, ActorRef storeActor, ActorRef routeActor){
        this.system = system;
        this.storeActor = storeActor;
        this.routeActor = routeActor;
    }

    public Route createRoute(){
        return route(
                get(
                        () ->
                )
        )
    }
}
