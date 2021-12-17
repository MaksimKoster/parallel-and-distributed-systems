import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.server.Route;

import akka.japi.Pair;
import akka.pattern.Patterns;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import com.sun.xml.internal.ws.util.CompletedFuture;

import static org.asynchttpclient.Dsl.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.regex.Pattern;

import static akka.http.javadsl.server.Directives.*;

public class Router {

    static Duration TIMEOUT = Duration.ofSeconds(5);
    public static String PARAMETR_URL = "testUrl";
    public static String PARAMETR_COUNT = "count";

    public static Route createRoute(ActorMaterializer materializer, ActorSystem system, Http http, ActorRef cacheActor){
        return route(get(()->
                parameter(PARAMETR_URL, testUrl ->
                        parameter(PARAMETR_COUNT, count ->{
                            int numOfReq = Integer.parseInt(count);
                            Flow<HttpRequest, HttpRequest, NotUsed> flow = Flow.of(HttpRequest.class);
                            Flow<HttpRequest, Pair<String, Integer>, NotUsed> mapped = flow.map(req -> new Pair<>(testUrl, numOfReq));
                            Flow<HttpRequest, Long, NotUsed> m = mapped.mapAsync(1, p ->
                                    Patterns.ask(cacheActor, new CachingActor.GetMessage(testUrl), TIMEOUT).thenCompose(response ->{
                                        CompletionStage<Long> result;
                                        String res = String.valueOf(response);
                                        Long resLong = Long.parseLong(res);
                                        if(resLong != -1) {
                                            result = CompletableFuture.completedFuture(resLong);
                                        } else {
                                            Flow<Pair<String, Integer>, Pair<String, Integer>, NotUsed> f = Flow.create();
                                            Flow<Pair<String, Integer>, Pair<String, Integer>, NotUsed> flowConc = f.mapConcat(reqEntity ->{
                                                ArrayList<Pair<String, Integer>> list = new ArrayList<>(0);
                                                for (int i = 0; i < reqEntity.second(); ++i){
                                                    list.add(reqEntity);
                                                }
                                                return list;
                                            });
                                            Flow<Pair<String, Integer>, Long, NotUsed> flowTimer = flowConc.mapAsync(1, reqEntity -> {
                                                AsyncHttpclient asyncHttpclient = asyncHttpClient();
                                            })
                                        }
                                    })

                        }))
                )
    }

}
