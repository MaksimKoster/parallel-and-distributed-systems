import akka.actor.ActorRef;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import scala.Array;

import java.util.ArrayList;

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

            ArrayList<String> servers = new ArrayList<>();
            for (String child: zoo.getChildren(SERVERS_PATH, this)){
                servers.add(new String())
            }
        } catch (InterruptedException | KeeperException e) {
            System.out.println(e);
        }
    }
}