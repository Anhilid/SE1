package control;
import java.util.LinkedList;

/*
 * Vorteil: wenig Code
 * Nachteil: Container wird direkt schon erstellt, selbst wenn man das Objekt noch gar nicht braucht
 * private static final Container<Member> con = new Container<>();
 * private Container(){
    }
  * //wir brauchen noch eine Methode um auf den Container zugreifen zu können
    //die muss statisch, weil die sonst nur aufgerufen werden könnte, wenn ein Objekt existiert
    * public static COntainer getInstance(){return con;}
 */
public class Container< E extends Member> {
    //Singleton: von einer Klasse darf nur eine Instanz geben
    private static Container<Member> con;

    //Container Konstruktor private, damit nur in dieser Klasse aufrufen können
    private Container(){
    }

    /*
     * Vorteil: man hat den Container nur, wenn man ihn auch wirklich braucht
     * Nachteil: evtl. gehen die Daten dann verloren
     */
    //synchronized heißt: in diesen Block kommt nur ein Objekt zur Laufzeit rein aller anderen kommen da nicht rein
    //Nachteil von synchronized: lange Laufzeiten
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
    private control.PersistenceStrategy PersistenceStrategy;
    private PersistenceStrategy<Member> sta = null;
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

    public Member getMember(Integer id) {
        for ( Member rec : MemberObjects) {
            if (id == rec.getID().intValue() ){
                return rec;
            }
        }
        return null;
    }

    public void setSta(PersistenceStrategy<Member> stra){this.sta = stra;}

    /*
     * die aktuell in einem Container-Objekt hinzugefügten Member-Objekte persistent auf Datenspeicher speichern
     */
    public void store() throws PersistenceException {
        if(sta == null)
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No strategy");
        sta.save(MemberObjects);
    }

    /*
     * laden von Member-Objekten auch nach Neustart mit der Methode möglich sein
     * befinden sich zu dem Zeitpunkt des Ladens Member-objekte in der Liste, sollen diese einfach überschrieben werden
     */
    public void load() throws PersistenceException{
        if(sta == null){
            throw new PersistenceException(PersistenceException.ExceptionType.NoStrategyIsSet, "No Strategy");
        }
        try {
            sta.load();
        } catch (Exception UnsupportedOperationException){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "No Implementation not available!");
        }
    }

}
