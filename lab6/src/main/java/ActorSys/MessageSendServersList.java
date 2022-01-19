package ActorSysMes;

import java.util.ArrayList;

public class MessageSendServersList {
    private final ArrayList<String> servers;

    public MessageSendServersList(ArrayList<String> serv){
        this.servers = serv;
    }

    public ArrayList<String> getServers(){
        return servers;
    }
}
