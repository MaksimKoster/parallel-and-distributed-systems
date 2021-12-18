import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StoreActor extends AbstractActor {
    public static final String RECIEVE_MESSAGE = "recieve test";

    private Map<Integer, ArrayList<String>> store = new HashMap<>();

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

    public static class GetMessage{
        private int packetID;

        GetMessage(int packetID){
            this.packetID = packetID;
        }

        public int getPacketID(){
            return packetID;
        }
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage.class, m -> {
                    ArrayList<String> res = store.get(m.getPacketID());
                    if (res == null){
                        res = new ArrayList<>(0);
                    }
                    res.add(m.getRes());
                    System.out.println(m.getRes());
                    store.put(m.getPacketID(), res);
                    System.out.println(RECIEVE_MESSAGE);
                })
                .build();
    }
}
