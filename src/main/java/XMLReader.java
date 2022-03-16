import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.util.ArrayList;

public class XMLReader {
    private ArrayList<Step> steps = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private String[][] gameField = {{"-", "-", "-"},
            {"-", "-", "-"},
            {"-", "-", "-"}};
    private static String status;

    public void readXML(String readingFile) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = factory.createXMLEventReader(new FileInputStream(readingFile));
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals("Player")) {
                        int attributeId = Integer.parseInt(startElement.getAttributeByName(new QName("id")).getValue());
                        String attributeName = startElement.getAttributeByName(new QName("name")).getValue();
                        String attributeSymbol = startElement.getAttributeByName(new QName("symbol")).getValue();
                        players.add(new Player(attributeId, attributeName, attributeSymbol));
                    } else if (startElement.getName().getLocalPart().equals("Step")) {
                        event = reader.nextEvent();
                        int attributeNum = Integer.parseInt(startElement.getAttributeByName(new QName("num")).getValue());
                        int attributePlId = Integer.parseInt(startElement.getAttributeByName(new QName("playerId")).getValue());
                        int cell = getCellAddress(event.asCharacters().getData());
                        steps.add(new Step(attributeNum, attributePlId, cell));
                    }
                }
            }
        } catch (Exception e) {
            ConsoleHelper.printMessage("The file with game steps wasn't found!" + System.lineSeparator(), true);
        }
    }

    public void playXMLGame(String readingFile) {
        readXML(readingFile);
        String symbol = "-";
        int playerId;
        Step currentStep;
        int currentCell;
        if (players.size() >= 2) {
            System.out.println();
            System.out.printf("Player 1 -> %s as '%s'" +
                    System.lineSeparator(), players.get(0).getName(), players.get(0).getSymbol());
            System.out.printf("Player 2 -> %s as '%s'" +
                    System.lineSeparator(), players.get(1).getName(), players.get(1).getSymbol());
        } else {
            ConsoleHelper.printMessage("The file with game result doesn't include all players!");
        }
        for (int i = 0; i < steps.size(); i++) {
            currentStep = steps.get(i);
            playerId = currentStep.getPlayerId();
            if (playerId == players.get(0).getId()) {
                symbol = players.get(0).getSymbol();
            }
            if (playerId == players.get(1).getId()) {
                symbol = players.get(1).getSymbol();
            }
            currentCell = (currentStep.getCell() - 1);
            gameField[currentCell / 3][currentCell % 3] = symbol;
            formatAndPrint(gameField, 800);
            System.out.println();
        }
        if (players.size() == 3) {
            Player winner = players.get(2);
            System.out.printf("Player %d -> %s is winner as '%s'!\n", winner.getId(), winner.getName(), winner.getSymbol());
        }
    }

    public void formatAndPrint(String gamefield[][], int pause) {
        StringBuilder resultString;
        for (int i = 0; i < 3; i++) {
            resultString = new StringBuilder("|");
            for (int j = 0; j < 3; j++) {
                resultString.append(gamefield[i][j] + "|");
            }
            System.out.println(resultString);
        }
        try {
            Thread.sleep(pause);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Integer getCellAddress(String value) {
        Integer cell = -1;
        try {
            cell = Integer.parseInt(value);
        } catch (NumberFormatException ignored) {
            String[] cellAdr = value.trim().split(" ");
            if (cellAdr.length == 2) {
                try {
                    cell = 3 * (Integer.parseInt(cellAdr[0]) - 1) + (Integer.parseInt(cellAdr[1]));
                } catch (Exception e) {
                    ConsoleHelper.printMessage("Unidentified coordinate format! Use only double with space (from '1 1' to '3 3') or one digit coordinate (from 1 to 9)");
                }
            }
        }
        return cell;
    }
}
