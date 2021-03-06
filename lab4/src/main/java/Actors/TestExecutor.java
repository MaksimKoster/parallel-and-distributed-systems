package Actors;

import Actors.StoreActor;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class TestExecutor extends AbstractActor {
    ActorRef storeActore;

    TestExecutor(ActorRef storeActore){
        this.storeActore = storeActore;
    }

    String execute(Message m) throws Exception, NoSuchFieldException{
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(m.getJsScript());
        Invocable invocable = (Invocable) engine;
        String result = invocable.invokeFunction(m.getFunctionName(), m.getParams()).toString();
        return result;
    }

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(
                Message.class, m -> {
                    String result = execute(m);
                    storeActore.tell(new StoreActor.StoreMessage(m.getPackageid(), result), ActorRef.noSender());
                }
        ).build();
    }

    public static class Message{
        private int packageid;
        private String jsScript;
        private String functionName;
        private Object[] params;
        private String expectedResult;

        Message(int packageid, String jsScript, String functionName, Object[] params, String expectedResult){
            this.packageid = packageid;
            this.jsScript = jsScript;
            this.functionName = functionName;
            this.params = params;
            this.expectedResult = expectedResult;
        }

        public Object[] getParams() {
            return params;
        }

        public int getPackageid() {
            return packageid;
        }

        public String getExpectedResult() {
            return expectedResult;
        }

        public String getJsScript() {
            return jsScript;
        }

        public String getFunctionName() {
            return functionName;
        }
    }
}
