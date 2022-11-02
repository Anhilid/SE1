package control;

public class ContainerException extends Exception{

    private Integer id;

    public ContainerException(Integer id){super("Das Member-Objekt mit der ID " + id + " ist bereits vorhanden!");}
    public ContainerException() {
        super("NULL-Werte dürfen nicht aufgenommen werden!");
    }
}
