import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.FileOutputStream;
import java.util.List;

public class XMLWriter {

    public static void writeXML(List<Player> players, List<Step> steps, String fileName) {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            XMLEventWriter writer = factory.createXMLEventWriter(new FileOutputStream(fileName), "windows-1251");
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            XMLEvent tab = eventFactory.createDTD("\t");
            writer.add(eventFactory.createStartDocument("windows-1251", "1.0"));
            writer.add(end);
            writer.add(eventFactory.createStartElement("","", "Gameplay"));
            writer.add(end);
            for (Player player: players) {
                writer.add(tab);
                writePlayer(writer, player);
            }
            writer.add(tab);
            writer.add(eventFactory.createStartElement("","", "Game"));
            writer.add(end);
            for (Step step: steps) {
                writer.add(tab);
                writer.add(tab);
                writeStep(writer, step);
            }
            writer.add(tab);
            writer.add(eventFactory.createEndElement("","", "Game"));
            writer.add(end);

            Player win = null;
            for (Player player: players) {
                if (player.isWin()) {
                    win = player;
                }
            }
            if (!win.equals(null)) {
                writer.add(tab);
                writer.add(eventFactory.createStartElement("", "", "GameResult"));
                writer.add(eventFactory.createStartElement("", "", "Player"));
                writer.add(eventFactory.createAttribute("id", String.valueOf(win.getId())));
                writer.add(eventFactory.createAttribute("name", win.getName()));
                writer.add(eventFactory.createAttribute("symbol", String.valueOf(win.getSymbol())));
                writer.add(eventFactory.createEndElement("", "", "Player"));
                writer.add(eventFactory.createEndElement("", "", "GameResult"));
                writer.add(end);
            }
            writer.add(eventFactory.createEndElement("","", "Gameplay"));
            writer.add(end);
            writer.add(eventFactory.createEndDocument());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeStep(XMLEventWriter eventWriter, Step step) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        eventWriter.add(eventFactory.createStartElement("", "", "Step"));
        eventWriter.add(eventFactory.createAttribute("num", String.valueOf(step.getNum())));
        eventWriter.add(eventFactory.createAttribute("playerId", String.valueOf(step.getPlayerId())));
        eventWriter.add(eventFactory.createCharacters(String.valueOf(step.getCell() + 1)));
        eventWriter.add(eventFactory.createEndElement("", "", "Step"));
        eventWriter.add(end);
    }

    private static void writePlayer(XMLEventWriter eventWriter, Player player) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        eventWriter.add(eventFactory.createStartElement("", "", "Player"));
        eventWriter.add(eventFactory.createAttribute("id", String.valueOf(player.getId())));
        eventWriter.add(eventFactory.createAttribute("name",  player.getName()));
        eventWriter.add(eventFactory.createAttribute("symbol",String.valueOf(player.getSymbol())));
        eventWriter.add(eventFactory.createEndElement("", "", "Player"));
        eventWriter.add(end);
    }
}
