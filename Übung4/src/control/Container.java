package control;
import java.util.LinkedList;

public class Container< E extends Member> {
    private static Container<Member> con;
    private Container(){
    }

    public synchronized static Container<Member> getInstance(){
        if(con == null){
            con = new Container<>();
        }
        return con;
    }

    /*
     * Interne LinkedList zur Abspeicherung der Objekte
     */
    private LinkedList<Member> MemberObjects = new LinkedList<>(); //Key, value

    //Persistence Strategy
    private control.PersistenceStrategy stra;
    //sta = PersistenceStrategyStream();

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

    public LinkedList<Member> getCurrentList(){
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

    /*
     * die aktuell in einem Container-Objekt hinzugefügten Member-Objekte persistent auf Datenspeicher speichern
     */
    public void store() throws PersistenceException {
        stra.save(MemberObjects);
    }

    /*
     * laden von Member-Objekten auch nach Neustart mit der Methode möglich sein
     * befinden sich zu dem Zeitpunkt des Ladens Member-objekte in der Liste, sollen diese einfach überschrieben werden
     */
    public void loadforce() throws PersistenceException{
        try {
            stra.load();
        } catch (Exception UnsupportedOperationException){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "No Implementation not available!");
        }
    }

    public void loadmerge() throws PersistenceException {
        try {
            //TODO das muss was anderes sein
            stra.load();
        } catch (Exception UnsupportedOperationException){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "No Implementation not available!");
        }
    }
}
