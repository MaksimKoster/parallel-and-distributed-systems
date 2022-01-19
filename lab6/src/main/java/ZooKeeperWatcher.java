import ActorSys.MessageSendServersList;
import akka.actor.ActorRef;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.ArrayList;

public class ZooKeeperWatcher implements Watcher {
    public static String SERVERS_PATH = "/servers";
    public static String JOIN_FORM = "%s/%s";

    private ZooKeeper zoo;
    private ActorRef actConfig;

    ZooKeeperWatcher(ZooKeeper zoo, ActorRef actConfig) throws InterruptedException, KeeperException {
        this.zoo = zoo;
        this.actConfig = actConfig;
        byte[] data = this.zoo.getData(SERVERS_PATH, true, null);
        System.out.printf("servers data=%s", new String(data));
    }

    public static String joinPath(){
        
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        try {
            zoo.getChildren(SERVERS_PATH, this);

            ArrayList<String> servers = new ArrayList<>();
            for (String child: zoo.getChildren(SERVERS_PATH, this)){
                String joinPathChild = String.format(JOIN_FORM, SERVERS_PATH, child);
                String serv = new String(zoo.getData(joinPathChild, false, null));
                servers.add(serv);
            }
            actConfig.tell(new MessageSendServersList(servers), ActorRef.noSender());
        } catch (InterruptedException | KeeperException e) {
            System.out.println(e);
        }
    }
}
