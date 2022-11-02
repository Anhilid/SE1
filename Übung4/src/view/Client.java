package view;

import control.*;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Client {
    static Container<Member> container = Container.getInstance();

    public static void main(String[] args) throws ContainerException, PersistenceException {
        //Intialisierung der Sachen
        Container<Member> speicher = Container.getInstance();
        Client client = new Client();
        boolean run = true;

        System.out.println("Hier können Mitarbeiter verwaltet werden. Falls du nicht weißt was zu tun ist, dann frag doch nach hilfe.");

        while (run) {
            Scanner scanner = new Scanner(System.in);  // Create a Scanner object
            System.out.print("> ");
            String order = scanner.nextLine();  // Read user input
            switch (order) {
                case "help":
                    //TODO ist das richtig ?
                    MemberView.help();
                    break;
                case "enter":
                    enter();
                    break;
                case "store":
                    store();
                    break;
                case "load":
                    load();
                    break;
                case "dump":
                    //TODO Filter Map Reduce
                    MemberView.dump(speicher.getCurrentList());
                    break;
                case "search":
                    search();
                    break;
                case "test":
                    MemberView.dumptest(speicher.getCurrentList());
                    break;
                case "exit":
                    run = false;
                    break;
                default:
                    System.out.println("Not a valid order. Try typing help ;)");

            }
        }
    }
        // System.out.println("Username is: " + userName);  // Output user input

        //Member-Objekte mit getCurrentList auslesen und an MemberView übergeben
        private static void enter () throws ContainerException {
        ConcreteMember m;
            try {
                //TODO stuff and Exception
                //TODO
                System.out.println("Gib einen neuen Mitarbeiter ein. Du wirst alles abgefragt, keine Sorge.");
                int ID = scanID();
                String Vorname = scanVorname();
                String Nachname = scanNachname();
                String Rolle = scanRolle();
                Scanner scanner = new Scanner(System.in);
                System.out.println("Möchtest du eine Abteilung eingeben? (Ja/Nein)");
                String decisionA = scanner.next();
                if(decisionA.equals("Ja")){
                    String Abteilung = scanAbteilung();
                    LinkedList expertenliste = scanExpertisen();
                    if(expertenliste != null) {
                        m = new ConcreteMember(ID, Vorname, Nachname, Rolle, Abteilung, expertenliste);
                    } else {
                        m = new ConcreteMember(ID, Vorname, Nachname, Rolle, Abteilung);
                    }

                } else {
                    LinkedList expertenliste = scanExpertisen();
                    if (expertenliste != null) {
                        m = new ConcreteMember(ID, Vorname, Nachname, Rolle, expertenliste);
                    } else {
                        m = new ConcreteMember(ID, Vorname, Nachname, Rolle);
                    }
                }
                container.addMember(m);
                System.out.println("Member erfolgreich hinzugefügt!");
            } catch (ContainerException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e){
                System.out.println("Das hat nicht funktioniert! Versuch es nochmal.");
            }
            //TODO Expertisen allgemein noch machen
        }

        private static int scanID () throws ContainerException {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Eindeutige ID: ");
            int ID = scanner.nextInt();
            //TODO funktioniert das so mit der Exception?
            if (container.contains(ID)) {
                throw new ContainerException(ID);
            }
            return ID;
        }

        private static String scanVorname(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Vorname: ");
            String vorname = scanner.nextLine();
            //TODO Bindestrich und Leerzeichen sollten auch okay sein
            if(isValidName(vorname)){
                throw new IllegalArgumentException("Der Vorname darf nur Buchstaben beinhalten");
            } else {
                return vorname;
            }
        }

        private static String scanNachname(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Nachname: ");
            String nachname = scanner.nextLine();
            if(isValidName(nachname)){
                throw new IllegalArgumentException("Der Vorname darf nur Buchstaben beinhalten");
            } else {
                return nachname;
            }
        }

        private static String scanRolle(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Rolle: ");
            String rolle = scanner.nextLine();
            if(isValidName(rolle)){
                throw new IllegalArgumentException("Der Vorname darf nur Buchstaben beinhalten");
            } else {
                return rolle;
            }
        }

        private static String scanAbteilung(){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Abteilung: ");
            String abteilung = scanner.nextLine();
            if(isValidName(abteilung)){
                throw new IllegalArgumentException("Der Vorname darf nur Buchstaben beinhalten");
            } else {
                return abteilung;
            }
        }

        //TODO evtl. als Hashmap, aber kann man dann filtern?
        private static class Expertisen{
            String expertise;
            int qualify;

            public Expertisen(String exp, int quali){
                this.expertise =exp;
                this.qualify = quali;
            }
        }
        private static LinkedList scanExpertisen(){
            System.out.println("Wie viele Expertisen möchtest du eingaben? (0/1/2/3)");
            Scanner scanner = new Scanner(System.in);
            int anzahl = scanner.nextInt();
            LinkedList ExpertisenListe = new LinkedList<>();
            if (anzahl == 0){
                return null;
            } else {
                for (int i = 0; i < anzahl; i++) {
                    System.out.println("Expertise "+ i+1 + ":");
                    String expertise = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Level: (Beginner: 1; Expert: 2, Top-Performer: 3)");
                    int quali = scanner.nextInt();
                    if(quali < 0 || quali > 4){
                        throw new IllegalArgumentException("Kein valides Level an Expertise!");
                    }
                    Client.Expertisen expertisen = new Expertisen(expertise, quali);
                    ExpertisenListe.add(expertisen);
                }
            }
            System.out.println("Expertisen erfolgreich hinzugefügt.");
            return ExpertisenListe;
        }

        public static void load () throws PersistenceException {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Sollen die geladenen Mitarbeiter mit dem Speicher vereinigt (merge) werden oder überschrieben (force) werden? ");
            String param = scanner.nextLine();
            if (param.equals("force")) {
                container.loadforce();
            }
            //TODO Das muss noch
            container.loadmerge();
        }

        public static void store() throws PersistenceException {
            container.store();
        }

        public static String search () {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Nach welcher Expertise soll gesucht werden? ");
            //TODO Fehlerhandling - richtige Expertise, mehrere, usw.
            try {
                String exp = scanner.nextLine();
                scanner.close();
                return exp;
            } catch (Exception e) {
                //TODO richtige Exception Handeling
                throw new IllegalArgumentException("Das geht nicht!");
            }
        }
    public static boolean isValidName(String test){
        if(test != null){
            //TODO je nach dem was der sagt was zugelassen werden soll
            //\p{IsWhite_Space}
            //[a-zA-Z]+
            //-
        }
                /*
                ((str != null) && (!str.equals(""))
                        && (str.matches("^[a-zA-Z]*$")));

                 */
        return false;
    }
}

