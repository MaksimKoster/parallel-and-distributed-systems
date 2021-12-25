import akka.actor.ActorRef;
import akka.actor.ActorSystem;

import java.time.Duration;

public class MainHttp {
    static final Duration TIMEOUT = Duration.ofSeconds(5);
    public static final String TEST_EXEC = "test execution started";

    ActorSystem system;
    ActorRef storeActor;

}
