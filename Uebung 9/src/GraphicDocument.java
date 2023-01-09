public class GraphicDocument extends CoreDocument {
    int id;
    String inhalt;

    public GraphicDocument(String s){
        inhalt = s;
        id = DocumentGetSet.set();
    }

    public int size() {
        return 1200;
    }

    public Document get() {return this;    }
}
