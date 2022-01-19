import akka.actor.ActorRef;
import akka.http.javadsl.Http;
import org.apache.zookeeper.*;

import java.nio.charset.StandardCharsets;

public class HttpServer implements Watcher {

    private final Http http;
    private final ActorRef actConf;
    private final ZooKeeper zoo;
    private String Path;

    HttpServer(Http http, ActorRef actConf, ZooKeeper zoo, String port) throws InterruptedException, KeeperException {
        this.http = http;
        this.actConf = actConf;
        this.zoo = zoo;
        this.Path = port;
        zoo.create(
                ZooKeeperWatcher.joinPath(Path),
                Path.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL
        );
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
