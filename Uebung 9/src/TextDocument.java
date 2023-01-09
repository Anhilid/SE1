import java.nio.charset.StandardCharsets;

public class TextDocument extends CoreDocument{
    private int id;
    private String inhalt;
    private encodingType encoding;

    public TextDocument(String s, encodingType enc){
        inhalt = s;
        encoding = enc;
        id = DocumentGetSet.set();
    }

    public int size(){
        try {
            switch (encoding) {
                case UTF8:
                    return inhalt.getBytes(StandardCharsets.UTF_8).length;
                case UTF32:
                    return inhalt.getBytes("UTF-32").length;
                case UTF16:
                    return inhalt.getBytes(StandardCharsets.UTF_16).length;
            }
        } catch (Exception e){
            e.getMessage();
            throw new UnsupportedOperationException("No length able to calculate");
        }
        return 0;
    }

    public Document get() {return this;
    }
}
