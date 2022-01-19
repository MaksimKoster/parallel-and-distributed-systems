import akka.actor.ActorRef;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class HttpServer implements Watcher {

    private final Http http;
    private final ActorRef actConf;
    

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
