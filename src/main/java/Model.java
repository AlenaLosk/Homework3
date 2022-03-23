public class Model {
    private String[][] gameField = new String[3][3];
    private final int GAME_CELLS = gameField.length * gameField[0].length;

    public void init() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameField[i][j] = "-";
            }
        }
    }

    public Step putSymbol(Player player) {
        int x = -1;
        Step step;
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
        if (gameField[x / 3][x % 3].equals("-")) {
            gameField[x / 3][x % 3] = player.getSymbol();
            step = new Step(player.getId(), x + 1);
        } else {
            ConsoleHelper.printMessage("This cell isn't free! Please, try again!", true);
            step = putSymbol(player);
        }
        return step;
    }

    public boolean hasFreeCell(String[][] gameField) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameField[i][j].equals("-")) return true;
            }
        }
        return false;
    }

    public boolean isWin(Player player) {
        String symbol = player.getSymbol();
        for (int i = 0; i < 3; i++) {
            if ((gameField[i][0].equals(symbol) && gameField[i][1].equals(symbol) && gameField[i][2].equals(symbol)) ||
                    (gameField[0][i].equals(symbol) && gameField[1][i].equals(symbol) && gameField[2][i].equals(symbol))) {
                return true;
            }

            if (gameField[0][0].equals(symbol) && gameField[1][1].equals(symbol) && gameField[2][2].equals(symbol)) return true;
            if (gameField[0][2].equals(symbol) && gameField[1][1].equals(symbol) && gameField[2][0].equals(symbol)) return true;
        }
        return false;
    }

    public Player createPlayer(int id, String name, String symbol) {
        return new Player(id, name, symbol);
    }

    public String[][] getGameField() {
        return gameField;
    }
}
