package view;

import control.Container;
import control.Member;

import java.util.List;
import java.util.Scanner;

public class MemberView {

    public <liste> void dump (List<Member> liste){

        for (Member rec:liste) {
            System.out.println(rec.toString());
        }
    }
}
