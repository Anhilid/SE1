package control;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

//Zustandsbasierter Test
class ContainerTest {
    Container container;
    Member m1;
    Member m2;
    Member m3;

    @BeforeEach
    void init(){
        container = new Container();
    }

    @AfterEach
    void teardown(){
        container = null;
    }

    @Test
    void test_Add() throws ContainerException {
        try {
            assertEquals(0, container.size());
            container.addMember(m1);
            assertEquals(1, container.size());
            container.addMember(m2);
            container.addMember(m3);
            assertEquals(3, container.size());
            container.addMember(m2);
        //hier kann man auch mit try catch arbeiten, anstatt mit assert Throws
        } catch (Exception e){
            assertThrows()
        }
    }

}