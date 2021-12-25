import akka.actor.ActorSystem;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{
        ActorSystem system = ActorSystem.create("routes");
    }
}
