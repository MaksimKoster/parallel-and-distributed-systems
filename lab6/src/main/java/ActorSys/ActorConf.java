package ActorSys;

import akka.actor.AbstractActor;

import java.util.ArrayList;
import java.util.Random;

final class MessageGetRandomServerUrl{
    MessageGetRandomServerUrl(){}
}

public class ActorConf extends AbstractActor {
    private ArrayList<String> servers = new ArrayList<>();
    private Random rand = Random();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        MessageGetRandomServerUrl.class,
                        msg -> sender().tell(
                                g
                        )
                )
    }

    public getRandomPort(){

    }
}
