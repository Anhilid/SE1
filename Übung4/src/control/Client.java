package control;

import Model.*;
import view.MemberView;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Client implements Serializable {
    static Container container;

    public Client(Container container) {
        this.container = container;
    }

    public static void enter() throws ContainerException {
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
            if (decisionA.equals("Ja")) {
                String Abteilung = scanAbteilung();
                LinkedList expertenliste = scanExpertisen();
                if (expertenliste != null) {
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
        } catch (Exception e) {
            System.out.println("Das hat nicht funktioniert! Versuch es nochmal.");
        }
        //TODO Expertisen allgemein noch machen
    }

    private static int scanID() throws ContainerException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Eindeutige ID: ");
        int ID = scanner.nextInt();
        //TODO funktioniert das so mit der Exception?
        if (container.contains(ID)) {
            throw new ContainerException(ID);
        }
        return ID;
    }

    private static String scanVorname() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vorname: ");

        String vorname = scanner.nextLine();
        //TODO Bindestrich und Leerzeichen sollten auch okay sein
        if (!isValid(vorname)) {
            throw new IllegalArgumentException("Der Vorname darf nur Buchstaben beinhalten");
        } else {
            return vorname;
        }
    }

    private static String scanNachname() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nachname: ");
        String nachname = scanner.nextLine();
        if (!isValid(nachname)) {
            throw new IllegalArgumentException("Der Vorname darf nur Buchstaben beinhalten");
        } else {
            return nachname;
        }
    }

    /*
    JFrame meinJFrame = new JFrame();
    meinJFrame.setTitle("JComboBox Beispiel");
    meinJFrame.setSize(250, 250);
    JPanel panel = new JPanel();

    JLabel frage = new JLabel("Aus welchem Bundesland kommst du?");
    panel.add(frage);

    // Array für unsere JComboBox
    String comboBoxListe[] = {"Baden-Württemberg", "Bayern",
        "Berlin", "Brandenburg", "Bremen",
        "Hamburg", "Hessen", "Mecklenburg-Vorpommern",
        "Niedersachsen", "Nordrhein-Westfalen", "Rheinland-Pfalz",
        "Saarland", "Sachsen", "Sachsen-Anhalt",
        "Schleswig-Holstein", "Thüringen"};

    //JComboBox mit Bundesländer-Einträgen wird erstellt
    JComboBox bundeslandAuswahl = new JComboBox(comboBoxListe);

    //JComboBox wird Panel hinzugefügt
    panel.add(bundeslandAuswahl);

    meinJFrame.add(panel);
    meinJFrame.setVisible(true);

}
     */
    private static String scanRolle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Rolle: ");
        String rolle = scanner.nextLine();
        if (!isValid(rolle)) {
            throw new IllegalArgumentException("Der Vorname darf nur Buchstaben beinhalten");
        } else {
            return rolle;
        }
    }

    private static String scanAbteilung() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Abteilung: ");
        String abteilung = scanner.nextLine();
        if (!isValid(abteilung)) {
            throw new IllegalArgumentException("Der Vorname darf nur Buchstaben beinhalten");
        } else {
            return abteilung;
        }
    }

    //TODO evtl. als Hashmap, aber kann man dann filtern?
    private static class Expertisen implements Serializable{
        String expertise;
        int qualify;

        public Expertisen(String exp, int quali) {
            this.expertise = exp;
            this.qualify = quali;
        }
    }

    private static LinkedList scanExpertisen() {
        System.out.println("Wie viele Expertisen möchtest du eingaben? (0/1/2/3)");
        Scanner scanner = new Scanner(System.in);
        int anzahl = scanner.nextInt();
        LinkedList ExpertisenListe = new LinkedList<>();
        if (anzahl == 0) {
            return null;
        } else {
            for (int i = 0; i < anzahl; i++) {
                System.out.println("Expertise " + i + 1 + ":");
                String expertise = scanner.nextLine();
                scanner.nextLine();
                System.out.println("Level: (Beginner: 1; Expert: 2, Top-Performer: 3)");
                int quali = scanner.nextInt();
                if (quali < 0 || quali > 4) {
                    throw new IllegalArgumentException("Kein valides Level an Expertise!");
                }
                Client.Expertisen expertisen = new Expertisen(expertise, quali);
                ExpertisenListe.add(expertisen);
            }
        }
        System.out.println("Expertisen erfolgreich hinzugefügt.");
        return ExpertisenListe;
    }

    public static void load() throws PersistenceException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sollen die geladenen Mitarbeiter mit dem Speicher vereinigt (merge) werden oder überschrieben (force) werden? ");
        String param = scanner.nextLine();
        if (param.equals("force")) {
            container.loadForce();
        } else {
            container.loadMerge();
        }
    }

    public static void store() throws PersistenceException {
        System.out.println("Storing....");
        container.store();
    }

    public static void search(List list) {
        try {
            if(list == null){
                throw new IllegalArgumentException("Es ist noch keine Liste eingetragen");
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("Wonach soll gesucht werden - Expertisen oder Abteilungen?");
            String dec = scanner.nextLine();
            if (dec.equals("Abteilungen")) {
                System.out.println("Nach welcher Abteilung soll gesucht werden? ");
                String exp = scanner.nextLine();
                //scanner.close();
                MemberView.dumpAbteilung(list, exp);
                System.out.println("Such Liste erfolgreich gedumpt");
            } else if (dec.equals("Expertisen")) {
                //for (Object rec: list) {
                  //  System.out.println(((Member) rec).toStringExpertisen());
                //}
                System.out.println("Nach welcher Expertise soll gesucht werden? ");
                String exp = scanner.nextLine();
                //scanner.close();
                MemberView.dumpExpertisen(list, exp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static boolean isValid(String test) {
        if(test != null) {
            if (test.matches("[A-Za-z]+")){
                if (!test.equals("")) {
                    return true;
                }
            }
        }
        return false;
    }
}

