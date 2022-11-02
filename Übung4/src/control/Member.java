package control;

public interface Member {

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
        //String getExpertise();

        public String toString();

        public String make();

}
