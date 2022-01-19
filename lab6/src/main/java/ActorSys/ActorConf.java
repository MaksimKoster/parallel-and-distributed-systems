package ActorSys;

import akka.actor.AbstractActor;

import java.util.ArrayList;

final class MessageGetRandomServerUrl{
    MessageGetRandomServerUrl(){}
}

public class ActorConf extends AbstractActor {
    private ArrayList<String> servers = new ArrayList<>();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        Message
                )
    }
}
