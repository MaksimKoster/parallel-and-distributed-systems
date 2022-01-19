import akka.actor.ActorRef;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperWatcher implements Watcher {
    public static String SERVERS_PATH = "/servers";

    ZooKeeperWatcher(ZooKeeper zoo, ActorRef actConfig)

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
