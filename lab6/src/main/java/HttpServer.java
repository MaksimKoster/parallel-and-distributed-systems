import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import org.apache.zookeeper.*;
import ActorSys.*;


import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static akka.http.javadsl.server.Directives.*;

public class HttpServer implements Watcher {
    private static final String QUOTES = "";
    private static final String PARAM_URL = "url";
    private static final String PARAM_COUNT = "count";
    private static final Duration TIMEOUT = Duration.ofMillis(5000);
    private static final String URL_PATTERN = 


    private final Http http;
    private final ActorRef actConf;
    private final ZooKeeper zoo;
    private final String path;

    HttpServer(Http http, ActorRef actConf, ZooKeeper zoo, String port) throws InterruptedException, KeeperException {
        this.http = http;
        this.actConf = actConf;
        this.zoo = zoo;
        this.path = port;
        zoo.create(
                ZooKeeperWatcher.joinPath(path),
                path.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL
        );
    }

    public Route createRoute(){
        return route(path(QUOTES, () ->
                route(get( () -> parameter(PARAM_URL, (url) ->
                        parameter(PARAM_COUNT, (count) ->
                                completeWithFuture(count.equals("0") ? http.singleRequest(HttpRequest.create(url))
                                                        : Patterns.ask(
                                                        actConf,
                                                        new MessageGetRandomServerUrl(),
                                                        TIMEOUT
                                                )
                                                .thenCompose(
                                                        resPort -> http.singleRequest(HttpRequest.create(
                                                                String.format("jj")
                                                        ))
                                                )

                                                )))

                ))));
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            zoo.getData(path, this, null);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }
}
