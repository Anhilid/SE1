package control;


import view.Client;

public class Main {
    public static void main(String[] args){
        Container speicher = Container.getInstance();
        speicher.setSta(new PersistenceStrategyStream<Member>());
        Client test = new Client();
    }
}
