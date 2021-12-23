import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import java.util.ArrayList;

public class TestExecutor extends AbstractActor {
    ActorRef storeActore;

    TestExecutor(ActorRef storeActore){
        this.storeActore = storeActore;
    }

    @Override
    public Receive createReceive() {
        return null;
    }

    public static class Message{
        private int packageid;
        private String jsScript;
        private String functionName;
        private ArrayList<PackageTests.Test> tests;
    }
}
