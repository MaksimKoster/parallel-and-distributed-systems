package ActorSys;

import akka.actor.AbstractActor;

import java.util.ArrayList;

public class ActorConf extends AbstractActor {
    private ArrayList<String> servers = new ArrayList<>();

    @Override
    public Receive createReceive() {
        return null;
    }
}
