import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.HashMap;

public class CachingActor extends AbstractActor {
    HashMap

    @Override
    public Receive createRecieve(){
        return ReceiveBuilder.create()
                .match(StoreMessage.class , m ->
                {
                   
                })
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
