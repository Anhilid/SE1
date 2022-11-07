package Model;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Container implements Serializable {
    private static Container con = null;
    /*
     * Interne LinkedList zur Abspeicherung der Objekte
     */
    private List<Member> MemberObjects = null; //Key, value


    //Persistence Strategy
    private Model.PersistenceStrategy PersistenceStrategy;
    private Model.PersistenceStrategy stra;

    private Container(){
        MemberObjects = new LinkedList<Member>();
    }

    public static synchronized Container getInstance(){
        if(con == null){
            con = new Container();
        }
        return con;
    }


    /*
     * Methode zum Hinzufuegen einer Member.
     * @throws ContainerException
     */
    public void addMember(Member r) throws ContainerException {
        if(r == null){
            ContainerException ex = new ContainerException();
            throw ex;
        }
        Integer ID = r.getID();
        //erstmal durch die Liste durch und prüfen ob das Member Objekt schon da ist
        if(contains(ID)){
            throw new ContainerException(ID);
        }
        MemberObjects.add(r);
    }

    public boolean contains(int id) throws ContainerException {
        for (Member rec:MemberObjects) {
            if(rec.getID().intValue() == id){
                ContainerException ex = new ContainerException(id);
                throw ex;
            }
        }
        return false;
    }

    public String deleteMember(Integer id) {
        try{
            //evtl. muss ich hier erst getMember(id) machen und fragen ob das Null ist
            Member tmp = getMember(id);
            MemberObjects.remove(tmp);
            return "Member-Objekt mit der ID " + id + " ist gelöscht!";
        } catch (Exception e){
            throw new IllegalArgumentException("Das Member-Objekt mit der ID " + id + " gibt es nicht!");
        }
    }

    public List getCurrentList(){
        return MemberObjects;
    }

    /*
    public List<Member> getCurrentList(){
        //gibt Liste zurück
    }
*/
    public int size(){
        return MemberObjects.size();
    }

    public Member getMember(Integer id) {
        for ( Member rec : MemberObjects) {
            if (id == rec.getID().intValue() ){
                return rec;
            }
        }
        return null;
    }


    public void setSta(Model.PersistenceStrategy<Member> stra){this.stra = stra;}
    /*
     * die aktuell in einem Container-Objekt hinzugefügten Member-Objekte persistent auf Datenspeicher speichern
     */
    public void store() throws PersistenceException {
        if (this.stra == null)
            throw new PersistenceException( PersistenceException.ExceptionType.NoStrategyIsSet,
                    "Strategy not initialized");
        try {
            System.out.println("Storing MemberObjects List....");
            this.stra.save(MemberObjects);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /*
     * laden von Member-Objekten auch nach Neustart mit der Methode möglich sein
     * befinden sich zu dem Zeitpunkt des Ladens Member-objekte in der Liste, sollen diese einfach überschrieben werden
     */
    public void loadForce() throws PersistenceException{
        try {
            List<Member> liste = stra.load();
            MemberObjects = liste;
        } catch (Exception UnsupportedOperationException){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "No Implementation not available!");
        }
    }

    public List loadMerge() throws PersistenceException {
        try {
            //TODO das muss was anderes sein
            List<Member> listeLoad = stra.load();
            for (Member rec :listeLoad) {
                addMember(rec);
            }
            return getCurrentList();
        } catch (Exception UnsupportedOperationException){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "No Implementation not available!");
        }
    }
}
