package view;
import control.EnglishTranslator;
import control.GermanTranslator;

public class Assembler {

    public Assembler() {
        GermanTranslator germanTranslator = new GermanTranslator();
        ClientTranslator client = new ClientTranslator( germanTranslator );

        client.display(1);

        client.setTranslator(new EnglishTranslator());

        client.display(2);
    }

    public static void main(String[] args) {
        Assembler assembler = new Assembler();
    }

}