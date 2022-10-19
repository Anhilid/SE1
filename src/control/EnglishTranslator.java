package control;

public class EnglishTranslator implements Translator{

    public String date = "Okt/2022"; // Default-Wert

    /**
     * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
     */
    public String translateNumber( int number ) {
        // [ihr Source Code aus Übung 1-2]
        try {
            return translate(number);
        } catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException("Übersetzung der Zahl" + number + " nicht möglich " + Translator.version);
        }
    }

    /**
     * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
     */
    public void printInfo(){
        System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
    }

    /**
     * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Monat/Jahr (Beispiel: Okt/2022))
     * Das Datum sollte system-intern durch eine Control-Klasse gesetzt werden und nicht von externen View-Klassen
     */
    public void setDate( String date ) {
        this.date = date;
    }

    public String translate(int number){
        String [] t = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
        return t[number-1];
    }
}
