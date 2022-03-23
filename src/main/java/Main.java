public class Main {
    public static void main(String[] args) {
        ConsoleHelper.printMessage("Welcome to game 'TicTacToe' menu!", true);
        int menuPoint = 0;
        while (menuPoint == 0 || menuPoint == 1 || menuPoint == 2 || menuPoint == 3) {
            menuPoint = askMenuPoint();
            switch (menuPoint) {
                case 1:
                    Game game = new Game();
                    Writer writer1 = new JSONWriter();
                    Writer writer2 = new XMLWriter();
                    boolean willGameContinue = true;
                    while (willGameContinue) {
                        game.getGameplay().process();
                        writer1.write(game, "gameplay.json");
                        writer2.write(game, "gameplay.xml");
                        ConsoleHelper.printMessage("Do you want to play again?", true);
                        ConsoleHelper.printMessage("If no, enter '1'. For continue playing enter any symbol: ");
                        String wantToPlay = ConsoleHelper.readMessage();
                        if (wantToPlay.equals("1")) willGameContinue = false;
                    }
                    break;
                case 2:
                    Reader reader = new JSONReader();
                    reader.readAndPlay("gameplay.json");
                    menuPoint = 0;
                    break;
                case 3:
                    ConsoleHelper.printMessage("Menu is closed.");
                    menuPoint = 4;
                    break;
                default:
                    break;
            }
        }
    }

    private static int askMenuPoint() {
        int menuPoint = 0;
        while (menuPoint != 1 && menuPoint != 2 && menuPoint != 3) {
            ConsoleHelper.printMessage("Enter '1', if you want to play TicTacToe,"
                    + System.lineSeparator() + "Enter '2' - to see previous game"
                    + System.lineSeparator() + "Enter '3', if you want to exit: ");
            String temp = ConsoleHelper.readMessage();
            if (temp.equals("1") || temp.equals("2") || temp.equals("3")) {
                menuPoint = Integer.parseInt(temp);
            } else {
                ConsoleHelper.printMessage("Invalid input! Please, try again.", true);
            }
        }
        return menuPoint;
    }
}
