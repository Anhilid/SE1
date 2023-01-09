import java.util.ArrayList;
import java.util.List;

public class ComplexDocument implements Document {
    public List<Document> list = new ArrayList<>();

    public ComplexDocument() {
    }

    public void add(int id, ComplexDocument doc) {
        list.add(id, doc);
    }

    
    public void remove(int id){
        list.remove(id);
    }
    
    @Override
    //hier die Size soll in Bytes ausgedrückt werden, aber ich weiß nicht wie Bytes zu addieren sind
    //TODO
    public int size() {
        int accumulate = 0;
        for (Document i:list) {
            accumulate += i.size();
        }
        return accumulate;
    }
}
