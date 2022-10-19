package control;

public class Factory {
/*
 * Verwendung des Factory Method Design Pattern (GoF)
 * Problem: Inkonsistente Objekterzeugung an potentiell n verschiedenen Stellen
 * Lösung: Bereitstellung einer zentralen Klasse zur konsistenten Objekterzeugung
 * und -Parametrisierung
 */
    public static Translator generateTranslator(){
        //Datum noch setzten
        return new GermanTranslator();
    }
}
