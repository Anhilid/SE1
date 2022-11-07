package control;

import Model.*;
import view.MemberView;

import java.util.Scanner;

public class Main {
    static Container container = Container.getInstance();
    public static void main(String[] args) throws ContainerException, PersistenceException {
        //Intialisierung der Sachen
        Container speicher = Container.getInstance();
        Client client = new Client(container);
        boolean run = true;
        PersistenceStrategy stream = new PersistenceStrategyStream();

        speicher.setSta(stream);

        System.out.println("Hier können Mitarbeiter verwaltet werden. Falls du nicht weißt was zu tun ist, dann frag doch nach hilfe.");

        while (run) {
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            System.out.print("> ");
            String order = scanner.nextLine();  // Read user input
            switch (order) {
                case "help":
                    MemberView.help();
                    break;
                case "enter":
                    Client.enter();
                    break;
                case "store":
                    Client.store();
                    break;
                case "load":
                    Client.load();
                    break;
                case "dump":
                    //TODO Filter Map Reduce
                    MemberView.dump(speicher.getCurrentList());
                    break;
                case "search":
                    Client.search(speicher.getCurrentList());
                    break;
                case "exit":
                    run = false;
                    break;
                default:
                    System.out.println("Not a valid order. Try typing help ;)");

            }
        }
    }
}
