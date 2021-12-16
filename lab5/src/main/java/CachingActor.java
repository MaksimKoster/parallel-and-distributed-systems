import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;

public class CachingActor extends AbstractActor {
    HashMap<String, Long> cache = new HashMap<>();

    @Override
    public Receive createReceieve(){
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
                    }
                })
                .build();
    }

    public static class GetMessage{
        private String url;

        public String getUrl(){
            return url;
        }

        GetMessage(String url){
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

        StoreMessage(String url, Long res){
            this.url = url;
            this.res = res;
        }
    }
}
