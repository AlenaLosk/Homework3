import java.util.*;

public class Model {
    private final Symbol[][] GAME_FIELD = new Symbol[3][3];
    private final int GAME_CELLS = GAME_FIELD.length * GAME_FIELD[0].length;

    public void init() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GAME_FIELD[i][j] = Symbol.SPACE;
            }
        }
    }

    public void putSymbol(Symbol[][] gameField, Player player) {
        int x = -1;
        ConsoleHelper.printMessage(String.format("%dst player is moving", player.getId()), true);
        while (x < 0) {
            ConsoleHelper.printMessage(String.format("Enter the number of the cell (from 1 to %s): ", GAME_CELLS));
            try {
                x = Integer.parseInt(ConsoleHelper.readMessage()) - 1;
                if (x < 0 || x > 8) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                x = -1;
                ConsoleHelper.printMessage("This cell doesn't exist!", true);
            }
        }
        if (gameField[x / 3][x % 3] == Symbol.SPACE) gameField[x / 3][x % 3] = player.getSymbol();
        else {
            ConsoleHelper.printMessage("This cell isn't free! Please, try again!", true);
            putSymbol(gameField, player);
        }
    }

    public boolean hasFreeCell(Symbol[][] gameField) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameField[i][j] == Symbol.SPACE) return true;
            }
        }
        return false;
    }

    public boolean isWin(Symbol[][] gameField, Player player) {
        Symbol mark = player.getSymbol();
        for (int i = 0; i < 3; i++) {
            if ((gameField[i][0] == mark && gameField[i][1] == mark && gameField[i][2] == mark) ||
                    (gameField[0][i] == mark && gameField[1][i] == mark && gameField[2][i] == mark)) {
                return true;
            }

            if (gameField[0][0] == mark && gameField[1][1] == mark && gameField[2][2] == mark) return true;
            if (gameField[0][2] == mark && gameField[1][1] == mark && gameField[2][0] == mark) return true;
        }
        return false;
    }

    public Player createPlayer(int id, String name, Symbol symbol) {
        Player player = new Player(id, name, symbol);
        HashMap<Player, Integer[]> gameStatistic = Statistic.getGameStatistic();
        if (gameStatistic.isEmpty() || !gameStatistic.containsKey(player)) {
            gameStatistic.put(player, new Integer[]{0, 0, 0});
        }
        return player;
    }

    public Symbol[][] getGameField() {
        return GAME_FIELD;
    }
}
