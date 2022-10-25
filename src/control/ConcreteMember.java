package control;

public class ConcreteMember implements Member {
    Integer i = 0;
    Integer id = null;

    public ConcreteMember(Integer id) {
        this.id = id;
    }
    @Override
    public Integer getID() {
        return this.id;
    }

    private Integer getNewID(){
        return i++;
    }

    //oh hier muss ich glaube ich evtl nicht getID machen
    public String toString(){
        return "Member (ID = " + getID() +")";
    }
}
