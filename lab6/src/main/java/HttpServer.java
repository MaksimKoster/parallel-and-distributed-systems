import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.nio.charset.StandardCharsets;

public class HttpServer implements Watcher {

    private final Http http;
    private final ActorRef actConf;
    private final ZooKeeper zoo;
    private final String path;

    HttpServer(Http http, ActorRef actConf, ZooKeeper zoo, String port){
        this.http = http;
        this.actConf = actConf;
        this.zoo = zoo;
        this.path = port;
        zoo.create(
                ZooKeeperWatcher,
                path.getBytes(),
                
        )
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
