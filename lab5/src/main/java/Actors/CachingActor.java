package Actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;

public class CachingActor extends AbstractActor {
    HashMap<String, Long> cache = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(StoreMessage.class , m ->
                {
                    cache.put(m.url, m.res);
                })
                .match(GetMessage.class, m ->
                {
                    Long res = cache.get(m.getUrl());
                    if (res != null){
                        sender().tell(res, self());
                    } else {
                        sender().tell(-1, self());
                    }
                })
                .build();
    }

    public static class GetMessage{
        private String url;

        public String getUrl(){
            return url;
        }

        public GetMessage(String url){
            this.url = url;
        }
    }

    public static class StoreMessage{
        private String url;
        private Long res;

        public String getUrl(){
            return url;
        }

        public Long getResult(){
            return res;
        }

        public StoreMessage(String url, Long res){
            this.url = url;
            this.res = res;
        }
    }
}
