import akka.actor.AbstractActor;

public class StoreActor extends AbstractActor {

    public static class StoreMessage{
        private int packetID;
        private String res;

        public StoreMessage(int packetID, String res){
            this.packetID = packetID;
            this.res = res;
        }

        public String getRes(){
            return res;
        }

        public int getPacketID(){
            return packetID;
        }

    }

    @Override
    public Receive createReceive() {
        return null;
    }
}
