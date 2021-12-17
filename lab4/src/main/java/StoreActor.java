import akka.actor.AbstractActor;

public class StoreActor extends AbstractActor {

    public static class StoreMessage{
        private int packetID;
        private String res;


    }

    @Override
    public Receive createReceive() {
        return null;
    }
}
