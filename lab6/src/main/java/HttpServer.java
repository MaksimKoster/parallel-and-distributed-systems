import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class HttpServer implements Watcher {

    private final Http http;
    private final ActorRef actConf;
    private final ZooKeeper zoo;
    private String Path;

    HttpServer(Http http, ActorRef actConf, ZooKeeper zoo, String port)

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
