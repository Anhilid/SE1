package org.hbrs.s1.ws22.uebung1.control;

import java.net.ConnectException;

public class Container {
    Integer id;

    public void addMember(Member member) throws ConnectException {
        try{

        } catch (Exception e){
            throw new ContainerException("Das Member-Objekt mit der ID " + getID() + " ist bereits vorhanden!");
        }
    }

    public String deleteMember(Integer id) {
        try{

        } catch (Exception e){

        }
    }


    public Integer getID() {
        return id;
    }
}
