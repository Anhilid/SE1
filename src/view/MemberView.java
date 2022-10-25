package view;

import control.Member;

import java.util.List;

public class MemberView {

    /*
    public List<Member> getCurrentList(){
    //gibt Liste zurück
    }
     */
    public <liste> void dump (List<Member> liste){
        for (Member rec:liste) {
            System.out.println(rec.toString());
        }
    }
}
