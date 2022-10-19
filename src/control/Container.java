package control;

import java.net.ConnectException;
import java.util.ArrayList;

public class Container {
    private ArrayList MemberObjects;

    public void addMember(Member member) throws ContainerException {
        try{
            //erstmal durch die Liste durch und prüfen ob das Member Objekt schon da ist
            for(int i = 0; i < MemberObjects.size(); i++){
                if(member.equals(MemberObjects.get(i))){
                    throw new Exception();
                }
            }
            MemberObjects.add(member);
        } catch (Exception e){
            throw new ContainerException("Das Member-Objekt mit der ID " + member.getID() + " ist bereits vorhanden!");
        }
    }

    public String deleteMember(Integer id) {
        try{
            MemberObjects.remove(id);
            return "Member-Objekt mit der ID " + id + " ist gelöscht!";
        } catch (Exception e){
            throw new IllegalArgumentException("Das Member-Objekt mit der ID " + id + " gibt es nicht!");
        }
    }

    public void dump (){
        for (int i = 0; i < MemberObjects.size(); i++){
            System.out.println(MemberObjects.get(i));
        }
    }

    /*
    public List<Member> getCurrentList(){
        //gibt Liste zurück
    }
*/
    public int size(){
        return MemberObjects.size();
    }
}
