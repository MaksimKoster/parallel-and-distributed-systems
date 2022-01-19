import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import org.apache.log4j.BasicConfigurator;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import javax.annotation.processing.Completion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CompletionStage;

public class AnonymizeApp {

    public static int TIMEOUT = 5000;
    public static String HOST = "localhost";
    public static String URL_HOST = "http://" + HOST;

    public static void main(String[] args) {
        if (args.length < 2){
            System.err.println("No port argument");
            System.exit(-1);
        }
        String port = args[1];
        StringBuilder serversInfo = new StringBuilder("Servers online at ports\n");

        BasicConfigurator.configure();
        ActorSystem system = ActorSystem.create("lab6");
        ActorRef actConf = system.actorOf(Props.create(ActorSys.ActorConf.class));
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        final Http http = Http.get(system);
        ZooKeeper zoo = null;
        try {
            zoo = new ZooKeeper(args[0],5000 ,null);
            new ZooKeeperWatcher(zoo, actConf);
        } catch (InterruptedException | KeeperException | IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        ArrayList<CompletionStage<ServerBinding>> bindings = new ArrayList<>();
        try {
            HttpServer server = new HttpServer(http, actConf, zoo, port);
            final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow
                    = server.createRoute().flow(system, materializer);
            bindings.add(http.bindAndHandle(
                    routeFlow,
                    ConnectHttp.toHost(HOST),
                    materializer
            ));
            serversInfo.append(URL_HOST + port +"\n");
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
