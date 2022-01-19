import akka.actor.ActorRef;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperWatcher implements Watcher {
    public static String SERVERS_PATH = "/servers";

    private ZooKeeper zoo;
    private ActorRef actConfig;

    ZooKeeperWatcher(ZooKeeper zoo, ActorRef actConfig){
        this.zoo = zoo;
        this.actConfig = actConfig;
        byte[] data = this.zoo.getData(SERVERS_PATH, true, null);
        
    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
