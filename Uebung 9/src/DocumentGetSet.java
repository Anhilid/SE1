import java.util.Scanner;

public abstract class DocumentGetSet implements Document {
    public static int set() {
        System.out.println("Setze eine ID für das Dokument: ");
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        return id;
    }
    public abstract void get();
}
