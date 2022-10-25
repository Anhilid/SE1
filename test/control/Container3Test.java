package control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
            container.addMember(m1);
            container.addMember(m2);
            container.addMember(m3);
            container.setSta(PersistenceStrategyStream);
            container.store();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
