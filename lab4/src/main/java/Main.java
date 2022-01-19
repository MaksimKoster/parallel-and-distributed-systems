import akka.NotUsed;
import akka.actor.*;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.routing.RoundRobinPool;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.CompletionStage;

public class Main {
    public static String HOST = "localhost";
    public static int PORT = 8080;

    public static void main(String[] args) throws IOException{
        ActorSystem system = ActorSystem.create("routes");
        ActorRef storeActor = system.actorOf(Props.create(StoreActor.class));

        final SupervisorStrategy strategy = new OneForOneStrategy(
                5,
                Duration.ofMinutes(1),
                Collections.<Class <? extends  Throwable>> singletonList(Exception.class));
        
        ActorRef router = system.actorOf(new RoundRobinPool(5).withSupervisorStrategy(strategy).props(Props.create(TestExecutor.class, storeActor)));
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        MainHttp instance = new MainHttp(system, storeActor, router);
        final Flow<HttpRequest, HttpResponse, NotUsed> routerFlow = instance.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routerFlow,
                ConnectHttp.toHost(HOST, PORT),
                materializer
        );
        System.in.read();
        binding.thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }
}
