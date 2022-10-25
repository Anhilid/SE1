package control;

import java.io.*;
import java.lang.reflect.TypeVariable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PersistenceStrategyStream<E extends Member> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "C:\\Users\\chiar\\OneDrive\\2HBRS\\3 Semester\\SE1\\3";

    private ObjectInputStream ois = null;
    private FileInputStream fis = null;
    private List<Member> newListe =  null;

    private ObjectOutputStream oos = null;
    private FileOutputStream fos = null;
    
    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }



    @Override
    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save
     */
    public void openConnection() throws PersistenceException {
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        // fis = new FileInputStream( " a location to a file" );
        // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
        // ois = new ObjectInputStream(fis);
        try {
            fis = new FileInputStream(location);
            ois = new ObjectInputStream(fis);
        } catch (Exception e){
            //TODO Fehlermeldung
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Das hat nicht funktioniert");
        }
    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {
        try {
            ois.close();
        } catch (Exception e){
            //TODO
            throw new PersistenceException(PersistenceException.ExceptionType.ConnectionNotAvailable, "Das hat nicht funktioniert");
        }

    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<E> member) throws PersistenceException  {
        try {
            fos = new FileOutputStream(location);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(member);
        } catch (Exception e){
            //TODO Fehlermeldung
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Das hat nicht funktioniert!");
        }

    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException  {
        try {
            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                newListe = (List) obj;
            }
            return (List<E>) newListe;
        } catch (Exception e){
            throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "Das hat nicht funktioniert!");
        }
    }
}
