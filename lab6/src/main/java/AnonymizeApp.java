import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;
import org.apache.log4j.BasicConfigurator;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class AnonymizeApp {

    public static int TIMEOUT = 5000;

    public static void main(String[] args) {
        if (args.length < 2){
            System.err.println("No port argument");
            System.exit(-1);
        }
        BasicConfigurator.configure();
        ActorSystem system = ActorSystem.create("lab6");
        ActorRef actConf = system.actorOf(Props.create(ActorSys.ActorConf.class));
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        final Http http = Http.get(system);

        ZooKeeper zoo;
        try {
            zoo = new ZooKeeper(args[0],5000 ,null);
            new ZooKeeperWatcher(zoo, actConf);
        } catch (InterruptedException | KeeperException | IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        
    }
}
