package control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.Client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class Container3Test {
    Container container;

    ConcreteMember m1 = new ConcreteMember(1);

    ConcreteMember m2 = new ConcreteMember(2);
    ConcreteMember m3 = new ConcreteMember(3);
    ConcreteMember m4;
    private PersistenceStrategy PersistenceStrategyStream;

    @BeforeEach
    void init(){
        container = Container.getInstance();
    }

    @Test
    void test_Store() throws PersistenceException {
        //anstatt mit assertThrows kann man hier auch mit try catch arbeiten
        try {
            Client eingabe = new Client();
            eingabe.addMember(m1);
            eingabe.addMember(m2);
            eingabe.addMember(m3);
            container.setSta(PersistenceStrategyStream);
            container.store();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void test_MongoDB(){
        try{
            Client eingabe = new Client();
            eingabe.addMember(m1);
            eingabe.addMember(m2);
            container.setSta(new PersistenceStrategyMongoDB<Member>());
            assertThrows(UnsupportedOperationException.class, container::store);
        } catch (Exception e){
            System.out.println("Falsche Exception geworfen");
        }
    }

    @Test
    void test_FalscheLocation(){
        try {
            Client eingabe = new Client();
            eingabe.addMember(m1);
            control.PersistenceStrategyStream<Member> stra = new PersistenceStrategyStream<>();
            stra.setLocation("Falscher\\Pfad\\test.ser");
            container.setSta(stra);
            PersistenceException e = assertThrows(PersistenceException.class, container::store);
            //hiermit die Fehlermessage überprüfen
            assertEquals(e.getMessage(), "Das hat nicht funktioniert!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void testOhneStrategy(){

    }
}
