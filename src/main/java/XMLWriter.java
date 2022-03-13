import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class XMLWriter {

    public static void writeXML(String fileName) {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        try {
            XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(fileName));
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            StartElement configStartElement = eventFactory.createStartElement("",
                    "", "config");
            eventWriter.add(configStartElement);
            eventWriter.add(end);
            createNode(eventWriter, "mode", "1");
            createNode(eventWriter, "unit", "901");
            createNode(eventWriter, "current", "0");
            createNode(eventWriter, "interactive", "0");
            eventWriter.add(eventFactory.createEndElement("", "", "config"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }
}
