package org.hbrs.s1.ws22.uebung1.view;

import org.hbrs.s1.ws22.uebung1.control.GermanTranslator;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    //German-Translator-Objekt erstellen
    private GermanTranslator g1 = new GermanTranslator();

    //ein Client-Objekt erstellen
    private Client c1 = new Client(g1);

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
        c1.display(test1);
        assertEquals("Übersetzung der Zahl -123 nicht möglich 1.0" , outcontent.toString());
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
        c1.display(test3);
        assertEquals("Übersetzung der Zahl 123 nicht möglich 1.0" , outcontent.toString());
    }

    @Test
    //Test Case 4 in Dokumentation Übung1
    public void Test4() {
        int test4 = 0;
        c1.display(test4);
        assertEquals("Übersetzung der Zahl 0 nicht möglich 1.0" , outcontent.toString());
    }

    @After
    //den Out-Kanal wieder richtig legen
    public void restoreStreams(){
        System.setOut(orOut);
    }
}