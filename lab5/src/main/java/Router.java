import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.server.Route;

import akka.stream.ActorMaterializer;

import java.time.Duration;

import static akka.http.javadsl.server.Directives.*;

public class Router {

    static Duration TIMEOUT = Duration.ofSeconds(5);
    public static String PARAMETR_URL = "testUrl";
    public static String PARAMETR_COUNT = "count";

    public static Route createRoute(ActorMaterializer materializer, ActorSystem system, Http http, ActorRef cacheActor){
        return route(get(()->
                parameter(PARAMETR_URL)
                )
    }

}
