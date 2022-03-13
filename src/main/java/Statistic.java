import java.io.*;
import java.nio.charset.*;
import java.time.LocalDateTime;
import java.util.*;

public class Statistic {
    //Статистика по игрокам с одинаковым именем хранится только до конца работы программы
    // (но собирается в течение нескольких игр одной сессии работы программы)
    private static HashMap<Player, Integer[]> gameStatistic = new HashMap<>();

    static void setPlayerResult(Player player, Result result) {
        Integer[] tempResultArray = gameStatistic.get(player);
        switch (result) {
            case WIN:
                tempResultArray[0]++;
                break;
            case DRAW:
                tempResultArray[1]++;
                break;
            case LOSE:
                tempResultArray[2]++;
                break;
            default:
                break;
        }
    }

    public static HashMap<Player, Integer[]> getGameStatistic() {
        return gameStatistic;
    }

    public static void sendStatisticsToFile(ArrayList<String> strings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("game_statistic.txt", true))) {
            String title = System.lineSeparator() + "After game_party at " + LocalDateTime.now()
                    + System.lineSeparator() + "|NAME      |WINS      |DRAWS     |LOSES     |" + System.lineSeparator();
            writer.write(title);
            for (String s: makeOutputData()) {
                writer.write(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> makeOutputData() {
        ArrayList<String> result = new ArrayList<>();
        for (Map.Entry<Player, Integer[]> entry : gameStatistic.entrySet()) {
            StringBuilder sb = new StringBuilder("|");
            String name = entry.getKey().getName();
            Integer[] value = entry.getValue();
            sb.append(name);
            while (sb.length() < 11) {
                sb.append(" ");
            }
            sb.append("|");
            int len = 11;
            for (int i = 0; i < 3; i++) {
                sb.append(value[i]);
                while (sb.length() < len + 11) {
                    sb.append(" ");
                }
                len = len + 11;
                sb.append("|");
            }
            sb.append(System.lineSeparator());
            result.add(sb.toString());
        }
        Collections.sort(result);
        return result;
    }

}
