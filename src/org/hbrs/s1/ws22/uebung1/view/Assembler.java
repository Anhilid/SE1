package org.hbrs.s1.ws22.uebung1.view;

import org.hbrs.s1.ws22.uebung1.control.GermanTranslator;

//Die Klasse assembeld das ganze
public class Assembler {
    public void main (){
        //Dependence Injection (DI) Pattern siehe SE2
        //Client ist jetzt nicht mehr aktiv, sondern passiv
        GermanTranslator translator= new GermanTranslator(); //man könnte auch die Factory verwenden
        Client client = new Client(translator);
    }
}
