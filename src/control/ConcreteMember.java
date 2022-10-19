package control;

public class ConcreteMember implements Member {
    Integer i = 0;
    Integer id;

    public ConcreteMember(Member m){
        Integer id = getNewID();
        Member member = m;
    }
    @Override
    public Integer getID() {
        return id;
    }

    private Integer getNewID(){
        return i++;
    }

    //oh hier muss ich glaube ich evtl nicht getID machen
    public String toString(){
        return "Member (ID = " + getID() +")";
    }
}
