package view;

import Model.Member;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Ausgaben nur noch hier drüber
public class MemberView {

    //muss die Static?
    public static void help() {
        System.out.println("Enter   Eingabe eines Mitarbeiters");
        System.out.println("store   Abspeichern der vorhandenen Mitarbeiter");
        System.out.println("load   Laden von Mitarbeitern ");
        System.out.println("dump    Ausgabe aller Mitarbeiter");
        System.out.println("search  Suche nach Expertisen");
        System.out.println("exit    Verlassen der Anwendung");
    }


    public static class ComparatorAlphabetic implements Comparator<Member> {

        @Override
        public int compare(Member o1, Member o2) {
            //-1 if first ist less than second
            //TODO passt das so?
            return o1.getID().intValue() - o2.getID().intValue();
        }
    }

    //TODO das muss noch sortiert nach ID sein
    public static  <liste> void dump(List<Member> liste){
        //Tabellen-Form
        System.out.println("ID     Name");
        ComparatorAlphabetic alphabetic = new ComparatorAlphabetic();
        //über comparable machen
        liste.sort(alphabetic);

        System.out.printf("--------------------------------%n");
        System.out.printf("      Members alphabetisch      %n");
        System.out.printf("--------------------------------%n");


        //oder über Collections machen
        //Collections.sort(liste, alphabetic);
        for (Member rec:liste) {
            System.out.println(rec.toString());
        }
    }

    public static <liste> void dumpAbteilung(List<Member> liste, String abteilung) {
        try {
            Stream<Member> newStream = liste.stream()
                    .filter(empoly -> empoly.getAbteilung().equals(abteilung));
            List<Member> newList = newStream.toList();
            for (Member rec : newList) {
                System.out.println(rec.toString());
            }
            System.out.println("Liste erfolgreich ausgegeben");
        } catch (Exception e){
            throw new RuntimeException("Da hat etwas mit dem Filter nicht funktioniert.");
        }
    }

    public static <liste> void dumpExpertisen(List<Member> liste, String expertisen){
        try {
            Stream<Member> newStream = liste.stream()
                    .filter(empoly -> empoly.getExpertise().equals(expertisen));
            List<Member> newList = newStream.toList();
            for (Member rec : newList) {
                System.out.println(rec.toString());
            }
            System.out.println("Liste erfolgreich ausgegeben");
        } catch (Exception e){
            throw new RuntimeException("Da hat etwas mit dem Filter nicht funktioniert.");
        }
    }

/*
    public static <liste> void dumptest(List<Member> liste){
        for (Member rec:liste) {
            System.out.println(rec.make());
        }
    }
*/

    /*
     * das ist relativ hart, weil wie genau soll gesucht werden
     * das erklärt das relativ gut
     * https://www.baeldung.com/java-stream-filter-lambda
     */
    //erstmal nur eine Expertise
    /*
    public static <liste> void dump(List<Member> liste, String exp){
        List<Member> expertisenMA = liste
                .stream()
                .map(liste -> list.getName());
                .filter(liste -> list.g);
        Stream<Member> memberStream = liste.stream().filter(checkExpertise(exp));
        List list = memberStream.toList();
        for (Member rec:list) {
            System.out.println(rec.toString());
        }
    }

    for(Employee employee: list){
        System.out.println(employee.toString());
    }

    List<Employee> new List = this.liste.stream()
        .filter(employee -> employee.getAbteilung.equals("Abteilung"))
        .collect(Collection.toList());

    //evtl. muss das mit Class gemacht werden
    //class
    private boolean checkExpertise(){

    }
*/
}

