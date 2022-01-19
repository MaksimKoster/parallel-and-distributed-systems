import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class ZooKeeperWatcher implements Watcher {
    public static String SERVERS_PATH = "/servers";


    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
