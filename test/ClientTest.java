import control.GermanTranslator;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.ClientTranslator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    //German-Translator-Objekt erstellen
    private GermanTranslator g1 = new GermanTranslator();

    //ein Client-Objekt erstellen
    private ClientTranslator c1 = new ClientTranslator(g1);

    /*
     * Kanal des Outputs merken und dann verändern
     * Das was in den PrintStream geschrieben wird, kann man dann mit einem JUnit Test testen
     */
    private final ByteArrayOutputStream outcontent = new ByteArrayOutputStream();
    private final PrintStream  orOut = System.out;

    @BeforeEach
    //muss ein BeforeEach sein
    public void setUpStreams(){
        System.setOut(new PrintStream(outcontent));
    }

    @Test
    //Test Case 1 in Dokumentation Übung1
    public void Test1() {
        int test1 = -123;
        //assertEquals("Übersetzung der Zahl -123 nicht möglich 1.0" , outcontent.toString());
        assertThrows(IllegalArgumentException.class, () -> g1.translateNumber(test1));
    }

    @Test
    //Test Case 2 in Dokumentation Übung1
    public void Test2() {
        int test2 = 4;
        c1.display(test2);
        assertEquals("Das Ergebnis der Berechnung: vier" , outcontent.toString());
    }

    @Test
    //Test Case 3 in Dokumentation Übung1
    public void Test3() {
        int test3 = 123;
        assertThrows(IllegalArgumentException.class, () -> g1.translateNumber(test3));
    }

    @Test
    //Test Case 4 in Dokumentation Übung1
    public void Test4() {
        int test4 = 0;
        assertThrows(IllegalArgumentException.class, () -> g1.translateNumber(test4));
    }

    @After
    //den Out-Kanal wieder richtig legen
    public void restoreStreams(){
        System.setOut(orOut);
    }
}