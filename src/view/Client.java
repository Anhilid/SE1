package view;

import control.Container;
import control.ContainerException;
import control.Member;

import java.util.Scanner;

public class Client {
    Container<Member> container = Container.getInstance();


    //public addMember usw.
    public void addMember(Member r) throws ContainerException {
        if(r == null){
            ContainerException ex = new ContainerException();
            throw ex;
        }
        Integer ID = r.getID();
        //erstmal durch die Liste durch und prüfen ob das Member Objekt schon da ist
        for (Member rec: container.getCurrentList()) {
            if(rec.getID().intValue() == ID.intValue()){
                ContainerException ex = new ContainerException(r.getID().toString());
                throw ex;
            }
        }
        container.addMember(r);
    }

    public String deleteMember(Integer id) {
        try{
            //evtl. muss ich hier erst getMember(id) machen und fragen ob das Null ist
            Member tmp = container.getMember(id);
            container.deleteMember(tmp.getID());
            return "Member-Objekt mit der ID " + id + " ist gelöscht!";
        } catch (Exception e){
            throw new IllegalArgumentException("Das Member-Objekt mit der ID " + id + " gibt es nicht!");
        }
    }

    //Member-Objekte mit getCurrentList auslesen und an MemberView übergeben

}
