import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.server.Route;
import akka.pattern.PatternsCS;

import java.time.Duration;
import java.util.concurrent.CompletionStage;

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
                        () -> parameter("packageID", (parameter) ->{
                            int packageID = Integer.parseInt(parameter);
                            CompletionStage<Object> res = PatternsCS.ask(storeActor, new StoreActor.GetMessage(packageID), TIMEOUT);
                            return completeOKWithFuture(
                                    res,
                                    Jackson.marshaller()
                            );
                        })
                ),
                post(
                        () -> entity(Jackson.unmarshaller(PackageTests.class), (message) ->{
                            for(int i = 0; i < message.getTests().size(); ++i){
                                PackageTests.Test t = message.getTests().get(i);
                                routeActor.tell(
                                        new TestExecutor.Message(message.getPackageid(), message.getFunctionName(),
                                                message.getJsScript(), t.getParams(), t.getExpectedResult(), ActorRef.noSender())
                                );
                            }
                        })
                )
        )
    }
}
