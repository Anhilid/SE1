package control;
import java.util.LinkedList;

public class Container< E extends Member> {
    private static final Container<Member> con;

    static {
        con = new Container<>();
    }

    private control.PersistenceStrategy PersistenceStrategy;
    private PersistenceStrategy sta = null;
    private Container(){
    }
    public static Container<Member> getInstance(){
        return con;
    }

    /*
     * Interne LinkedList zur Abspeicherung der Objekte
     */
    private LinkedList<Member> MemberObjects = new LinkedList<>(); //Key, value

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
        for (Member rec:MemberObjects) {
            if(rec.getID().intValue() == ID.intValue()){
                ContainerException ex = new ContainerException(r.getID().toString());
                throw ex;
            }
        }
        MemberObjects.add(r);
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

    private Member getMember(Integer id) {
        for ( Member rec : MemberObjects) {
            if (id == rec.getID().intValue() ){
                return rec;
            }
        }
        return null;
    }

    public void setSta(PersistenceStrategy strategy){
        sta = strategy;
    }

    /*
     * die aktuell in einem Container-Objekt hinzugefügten Member-Objekte persistent auf Datenspeicher speichern
     */
    public void store() throws PersistenceException {
        //control.PersistenceStrategy.class.
        //control.PersistenceStrategyStream.class. .save(MemberObjects);
    }

    /*
     * laden von Member-Objekten auch nach Neustart mit der Methode möglich sein
     * befinden sich zu dem Zeitpunkt des Ladens Member-objekte in der Liste, sollen diese einfach überschrieben werden
     */
    public void load() throws PersistenceException{
        sta.load();
    }

}
