package ActorSys;

import ActorSysMes.MessageSendServersList;
import akka.actor.AbstractActor;
import akka.actor.Actor;

import java.util.ArrayList;
import java.util.Random;

final class MessageGetRandomServerUrl{
    MessageGetRandomServerUrl(){}
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
                                getRandomServerPort(),
                                Actor.noSender()
                        )
                )
                .match(
                        MessageSendServersList.class,
                        msg -> servers = msg.getServers()
                )
                .build();
    }

    public String getRandomServerPort(){
        int randserv = rand.nextInt(servers.size());
        System.out.println("All servers" + servers.toString());
        return servers.get(randserv);
    }

}

