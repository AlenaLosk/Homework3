public class Controller {
    private final Model model;
    private final View view;

    Player player1;
    Player player2;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void process() {
        boolean willGameContinue = true;
        while (willGameContinue) {
            ConsoleHelper.printMessage("Enter 1st player's name: ");
            String name = "";
            while (name.isEmpty()) {
                name = ConsoleHelper.readMessage();
            }
            player1 = model.createPlayer(1, name, Symbol.CROSS);
            name = "";
            ConsoleHelper.printMessage("Enter 2nd player's name: ");
            while (name.isEmpty()) {
                name = ConsoleHelper.readMessage();
                if (name.equals(player1.getName())) {
                    ConsoleHelper.printMessage("Don't repeat 1st player's name and enter 2nd player's name again: ");
                    name = "";
                }
            }
            player2 = model.createPlayer(2, name, Symbol.ZERO);
            model.init();
            Player currentPlayer = player1;
            boolean isWinnerFound = false;
            Integer[] tempResultArray;
            while (model.hasFreeCell(model.getGameField())) {
                model.putSymbol(model.getGameField(), currentPlayer);
                view.refresh(model.getGameField());
                if (model.isWin(model.getGameField(), currentPlayer)) {
                    ConsoleHelper.printMessage(String.format("Winner is %s!", currentPlayer.getName()), true);
                    Statistic.setPlayerResult(currentPlayer, Result.WIN);
                    Statistic.setPlayerResult(changePlayer(currentPlayer), Result.LOSE);
                    isWinnerFound = true;
                    break;
                }
                currentPlayer = changePlayer(currentPlayer);
            }
            if (!isWinnerFound) {
                ConsoleHelper.printMessage("Drawn game!", true);
                Statistic.setPlayerResult(currentPlayer, Result.DRAW);
                Statistic.setPlayerResult(changePlayer(currentPlayer), Result.DRAW);
            }
            Statistic.sendStatisticsToFile(Statistic.makeOutputData());
            ConsoleHelper.printMessage("Do you want to play again?", true);
            ConsoleHelper.printMessage("If no, enter word \"exit\". For continue playing enter any symbol: ");
            String wantToPlay = ConsoleHelper.readMessage();
            if (wantToPlay.equalsIgnoreCase("exit")) willGameContinue = false;
        }
    }

    public Player changePlayer(Player player) {
        if (player.equals(player1)) {
            return player2;
        } else {
            return player1;
        }
    }
}
