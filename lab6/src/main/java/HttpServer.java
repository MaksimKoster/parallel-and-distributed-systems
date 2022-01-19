import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

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
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
