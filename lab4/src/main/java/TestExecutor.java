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
        private Object[] params;
        private String expected;

        public Message(int packageid, String jsScript, String functionName,Object[] params, String expected){
            this.packageid = packageid;
            this.jsScript = jsScript;
            this.functionName = functionName;
            this.params = params;
            this.expected = expected;
        }

        public Object[] getParams() {
            return params;
        }

        public int getPackageid() {
            return packageid;
        }

        public String getExpected() {
            return expected;
        }

        public String getJsScript() {
            return jsScript;
        }

        public String getFunctionName() {
            return functionName;
        }
    }
}
