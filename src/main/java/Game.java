public class Game {
    public static void main(String[] args) {
        ConsoleHelper.printMessage("Welcome to game \'TicTacToe\' menu!", true);
        int menuPoint = 4;
        while (menuPoint >= 4 || menuPoint < 1) {
            menuPoint = askMenuPoint();
        }
        while (menuPoint == 1 || menuPoint == 2 || menuPoint == 3 || menuPoint == 4) {
            switch (menuPoint) {
                case 1:
                    Model model = new Model();
                    View view = new View();
                    Controller controller = new Controller(model, view);
                    if (controller.process() != true) {
                        menuPoint = 4;
                    }
                case 4:
                    menuPoint = askMenuPoint();
                    break;
                case 2:
                    try {
                        XMLReader.playXMLGame();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    menuPoint = 4;
                    break;
                case 3:
                    ConsoleHelper.printMessage("Menu is closed.");
                    menuPoint = 0;
                    break;
                default:
                    menuPoint = 4;
            }
        }
    }

    private static int askMenuPoint() {
        int menuPoint = 4;
        ConsoleHelper.printMessage("Enter \'1\', if you want to play TicTacToe,"
                + System.lineSeparator() + "Enter \'2\' - to see previous game"
                + System.lineSeparator() + "Enter \'3\', if you want to exit: ");
        try {
            menuPoint = Integer.parseInt(ConsoleHelper.readMessage());
        } catch (Exception ignored) {
            ConsoleHelper.printMessage("Invalid input! Please, try again.", true);
        }
        return menuPoint;
    }
}
