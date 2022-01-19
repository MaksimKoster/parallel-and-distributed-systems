import akka.actor.ActorRef;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZooKeeperWatcher implements Watcher {
    public static String SERVERS_PATH = "/servers";

    private ZooKeeper zoo;
    private ActorRef actConfig;

    ZooKeeperWatcher(ZooKeeper zoo, ActorRef actConfig) throws InterruptedException, KeeperException {
        this.zoo = zoo;
        this.actConfig = actConfig;
        byte[] data = this.zoo.getData(SERVERS_PATH, true, null);
        System.out.printf("servers data=%s", new String(data));
    }



    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            zoo.getChildren(SERVERS_PATH, this);
            
        } catch (InterruptedException | KeeperException e) {
            System.out.println(e);
        }
    }
}
