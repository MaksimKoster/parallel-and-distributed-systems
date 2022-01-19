import akka.http.javadsl.Http;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class HttpServer implements Watcher {

    private final Http http;


    HttpServer()

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
