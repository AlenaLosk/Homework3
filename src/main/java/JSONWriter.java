import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileOutputStream;

public class JSONWriter implements Writer {
    @Override
    public void write(Game game, String fileName) {
        ObjectMapper mapper = new ObjectMapper();
        try (FileOutputStream stream = new FileOutputStream(fileName)) {
            mapper.writerWithDefaultPrettyPrinter().writeValue(stream, game);
        } catch (Exception e) {
            ConsoleHelper.printMessage("The file for writing game steps wasn't found!" + System.lineSeparator(), true);
        }
    }
}
