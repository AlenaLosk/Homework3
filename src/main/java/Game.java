public class Game {
    public static void main(String[] args) {
        ConsoleHelper.printMessage("Welcome to game \'TicTacToe\' menu!", true);
        int menuPoint = 0;
        while (menuPoint == 0 ||menuPoint == 1 || menuPoint == 2 || menuPoint == 3) {
            menuPoint = askMenuPoint();
            switch (menuPoint) {
                case 1:
                    Model model = new Model();
                    View view = new View();
                    Controller controller = new Controller(model, view);
                    controller.process(new XMLWriter(), "gameplay.xml");
                    break;
                case 2:
                    new Reader(new XMLReader()).play("gameplay.xml");
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
            ConsoleHelper.printMessage("Enter \'1\', if you want to play TicTacToe,"
                    + System.lineSeparator() + "Enter \'2\' - to see previous game"
                    + System.lineSeparator() + "Enter \'3\', if you want to exit: ");
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
