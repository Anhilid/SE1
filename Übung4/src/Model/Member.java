package Model;
//Serializable muss sein, damit man es in einen Stream packen kann - also damit über Netzwerk schicken oder persitent speichern

import java.io.Serializable;
import java.util.LinkedList;

public interface Member extends Serializable {

        /**
         * ID ist über einen Konstruktor einer abgeleiteten Klasse
         * explizit außerhalb der Container-Klasse zu belegen
         *  -> Primärschlüssel zur Unterscheidung aller Member-Objekte
         */

        Integer getID();
        String getVorname();
        String getNachname();
        String getRolle();
        String getAbteilung();
        LinkedList getExpertise();

        public String toString();

        public String make();
        //public String toStringExpertisen();
        //public String toStringAbteilungen();

}
