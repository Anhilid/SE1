package Model;

import java.io.*;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private final String location = "objects.ser";
    //Zum Laden
    private ObjectInputStream ois = null;
    private FileInputStream fis = null;


    //Zum Speichern
    private ObjectOutputStream oos = null;
    private FileOutputStream fos = null;
    
    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    /*public void setLocation(String location) {
        this.location = location;
    }
    */


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
            fos = new FileOutputStream( location );
            fis = new FileInputStream( location );
        } catch (FileNotFoundException e) {
            throw new PersistenceException( PersistenceException.ExceptionType.ConnectionNotAvailable
                    , "Error in opening the connection, File could not be found");
        }
        try {
            oos = new ObjectOutputStream( fos );
            ois = new ObjectInputStream(  fis  );
        } catch (IOException e) {
            throw new PersistenceException( PersistenceException.ExceptionType.ConnectionNotAvailable
                    , "Error in opening the connection, problems with the stream");
        }
    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {

        try {
            // Closing the outputstreams for storing
            if (oos != null) oos.close();
            if (fos != null) fos.close();

            // Closing the inputstreams for loading
            if (ois != null) ois.close();
            if (fis != null) fis.close();
        } catch( IOException e ) {
            // Lazy solution: catching the exception of any closing activity ;-)
            throw new PersistenceException(PersistenceException.ExceptionType.ClosingFailure , "error while closing connections");
        }
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<E> members) throws PersistenceException, IOException {
        try {
            //this.openConnection();
            fos = new FileOutputStream(location);
            fis = new FileInputStream(location);
            oos = new ObjectOutputStream(fos);
            ois = new ObjectInputStream(fis);
            System.out.println("Connection open");
            oos.writeObject(members);
            System.out.println("Die Liste wurde erfolgreich gespeichert");
        } catch (IOException e) {
            // Koennte man ausgeben für interne Debugs: e.printStackTrace();
            // Chain of Responsibility: Hochtragen der Exception in Richtung Ausgabe (UI)
            // Uebergabe in ein lesbares Format fuer den Benutzer
            e.printStackTrace();
            throw new PersistenceException( PersistenceException.ExceptionType.SaveFailure , "Fehler beim Speichern der Datei!");
        }
        finally {
            //this.closeConnection();
            if (oos != null) oos.close();
            if (fos != null) fos.close();

            // Closing the inputstreams for loading
            if (ois != null) ois.close();
            if (fis != null) fis.close();
            System.out.println("Connection close");
        }
    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException {
        List<E> list = null;
        try {
            fis = new FileInputStream(location);
            ois = new ObjectInputStream(fis);
            System.out.println("Connection open");

            Object obj;
            obj = ois.readObject();
            if (obj instanceof List<?>) {
                list = (List) obj;
            }
            System.out.println("Liste wurde erfolgreich geladen");
            return list;
        } catch (IOException e) {
            // Sup-Optimal, da Exeception in Form eines unlesbaren Stake-Traces ausgegeben wird
            e.printStackTrace();
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "Fehler beim Laden der Datei!");
        } catch (ClassNotFoundException e) {
            // Chain of Responsbility erfuellt, durch Throw der Exceotion kann UI
            // benachrichtigt werden!
            throw new PersistenceException(PersistenceException.ExceptionType.LoadFailure, "Fehler beim Laden der Datei! Class not found!");
        } finally {
            this.closeConnection();
        }
    }
}
