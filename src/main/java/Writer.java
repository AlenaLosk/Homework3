import java.util.List;

public class Writer {
    private XMLWriter xmlWriter;

    public Writer(XMLWriter xmlWriter) {
        this.xmlWriter = xmlWriter;
    }

    public void write(List<Player> players, List<Step> steps, String fileName) {
        xmlWriter.writeXML(players, steps, fileName);
    }
}
