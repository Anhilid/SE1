package control;

public class ContainerException extends Exception{

    /*
     * wie kriege ich hier die ID?
     */
    /*
     * da die ContainerException Exception extended ist sie geprüft
     * die geprüften Excpetion müssen immer behandelt werden mit einem try-catch oder mit throws weiter werfen
     *
     * Exceptions werfen is eleganter als einfach nur einen String zurück geben
     * außerdem ist die Exception "verbindlicher", das ist wirklich ein Fehler und man kann mehr davon auslesen
     */
    private Integer id;

    public ContainerException(String id){super("Das Member-Objekt mit der ID " + id + " ist bereits vorhanden!");}
    public ContainerException() {
        super("NULL-Werte dürfen nicht aufgenommen werden!");
    }
}
