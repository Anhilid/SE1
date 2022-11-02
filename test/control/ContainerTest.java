package control;

import org.junit.jupiter.api.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

//Zustandsbasierter Test
class ContainerTest {
    Container container;

    ConcreteMember m1 = new ConcreteMember(1);

    ConcreteMember m2 = new ConcreteMember(2);
    ConcreteMember m3 = new ConcreteMember(3);
    ConcreteMember m4;
    @BeforeEach
    void init(){
        container = Container.getInstance();
    }


    @Test
    void test_Add() throws ContainerException {
        //anstatt mit assertThrows kann man hier auch mit try catch arbeiten
        try{
            container.addMember(m4);
        } catch (Exception e) {
            System.out.println(e);
        }
        //assertEquals(0, container.size());
        container.addMember(m1);
        assertEquals(1, container.size());
        container.addMember(m2);
        container.addMember(m3);
        assertEquals(3, container.size());
        try {
            container.addMember(m2);
        } catch (Exception e) {
            System.out.println(e);
        }
        assertEquals(3, container.size());
    }

    @Test
    void test_Delete() throws ContainerException{
        container.addMember(m1);
        container.addMember(m2);
        assertEquals(2, container.size());
        container.deleteMember(2);
        assertEquals(1, container.size());
        container.deleteMember(1);
        assertEquals(0, container.size());
    }
}