package control;

import java.util.LinkedList;

//Serializable muss sein, damit man es in einen Stream packen kann
public class ConcreteMember implements Member, java.io.Serializable {
    /*
     * Eigenschaften von Membern sind eindeutige ID, Vorname, Nachname,
     * Rolle im Unternehmen, Abteilung (falls vorhanden), Expertisen
     */
    Integer id = null;
    String Vorname;
    String Nachname;
    String Rolle;
    String Abteilung;
    LinkedList Expertise;
    //TODO Expertise

    public ConcreteMember(Integer id, String Vorname, String Nachname, String Rolle) {
        this.id = id;
        this.Vorname = Vorname;
        this.Nachname = Nachname;
        this.Rolle = Rolle;
    }

    public ConcreteMember(Integer id, String Vorname, String Nachname, String Rolle, String Abteilung) {
        this.id = id;
        this.Vorname = Vorname;
        this.Nachname = Nachname;
        this.Rolle = Rolle;
        this.Abteilung = Abteilung;
    }

    public ConcreteMember(Integer id, String Vorname, String Nachname, String Rolle, String Abteilung, LinkedList liste) {
        this.id = id;
        this.Vorname = Vorname;
        this.Nachname = Nachname;
        this.Rolle = Rolle;
        this.Abteilung = Abteilung;
        this.Expertise = liste;
    }

    public ConcreteMember(Integer id, String Vorname, String Nachname, String Rolle, LinkedList liste) {
        this.id = id;
        this.Vorname = Vorname;
        this.Nachname = Nachname;
        this.Rolle = Rolle;
        this.Abteilung = null;
        this.Expertise = liste;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    public String getVorname(){return Vorname;}
    public String getNachname(){return Nachname;}
    public String getRolle(){return Rolle;}
    public String getAbteilung(){return Abteilung;}
    public LinkedList getExpertise(){return Expertise;}

    //TODO ich glaube hier mit Tabelle arbeiten
    //TODO get das mit der Tabelle so
    public String toString(){
        return getID() +"       " + getNachname();
    }

    public String make(){
        return getID() + ", " + getVorname() + ", " + getNachname() + ", " + getRolle() + ", "  + getAbteilung() + ", " + getExpertise();
    }
}
