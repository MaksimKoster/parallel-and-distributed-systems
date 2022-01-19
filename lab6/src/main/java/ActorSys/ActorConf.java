package ActorSys;

import akka.actor.AbstractActor;
import akka.actor.Actor;

import java.util.ArrayList;
import java.util.Random;

public final class MessageGetRandomServerUrl{
    public MessageGetRandomServerUrl(){}
}

public class ActorConf extends AbstractActor {
    private ArrayList<String> servers = new ArrayList<>();
    private Random rand = new Random();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        MessageGetRandomServerUrl.class,
                        msg -> sender().tell(
                                getRandomPort(),
                                Actor.noSender()
                        )
                )
                .match(
                        MessageSendServersList.class,
                        msg -> servers = msg.getServers()
                )
                .build();
    }

    public String getRandomPort(){
        int pos = rand.nextInt(servers.size());
        return servers.get(pos);
    }
}
