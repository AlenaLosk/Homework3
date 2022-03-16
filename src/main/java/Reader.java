public class Reader {
    private XMLReader xmlReader;

    public Reader(XMLReader xmlReader) {
        this.xmlReader = xmlReader;
    }

    public void play(String readingFile) {
        xmlReader.playXMLGame(readingFile);
    }
}
