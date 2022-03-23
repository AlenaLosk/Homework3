import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonIgnoreProperties({"model", "view"})
public class Gameplay {
    private final Model model;
    private final View view;
    @JsonProperty("Player")
    private Player[] players;

    @JsonProperty("Game")
    private Steps steps;
    @JsonProperty("GameResult")
    private GameResult result;

    public Gameplay() {
        this.model = new Model();
        this.view = new View();
        steps = null;
        result = null;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Steps getSteps() {
        return steps;
    }

    public GameResult getResult() {
        return result;
    }

    public void setResult(GameResult result) {
        this.result = result;
    }

    public void process() {
        steps = new Steps(new ArrayList<Step>());
        result = new GameResult();
        int stepCounter = 1;
        if (players == null) {
            players = new Player[2];
            ConsoleHelper.printMessage("Enter 1st player's name: ");
            String name = "";
            while (name.isEmpty()) {
                name = ConsoleHelper.readMessage();
            }
            players[0] = model.createPlayer(1, name, "X");
            name = "";
            ConsoleHelper.printMessage("Enter 2nd player's name: ");
            while (name.isEmpty()) {
                name = ConsoleHelper.readMessage();
                if (name.equals(players[0].getName())) {
                    ConsoleHelper.printMessage("Don't repeat 1st player's name and enter 2nd player's name again: ");
                    name = "";
                }
            }
            players[1] = model.createPlayer(2, name, "O");
        }

        model.init();
        Player currentPlayer = players[0];
        while (model.hasFreeCell(model.getGameField())) {
            Step step = model.putSymbol(currentPlayer);
            step.setNum(stepCounter++);
            steps.getSteps().add(step);
            view.refresh(model.getGameField());
            if (model.isWin(currentPlayer)) {
                result.setWinner(currentPlayer);
                break;
            }
            currentPlayer = changePlayer(currentPlayer);
        }
        if (result.getWinner() == null) {
            ConsoleHelper.printMessage("Drawn game!", true);
        } else {
            ConsoleHelper.printMessage(String.format("Winner is %s!", result.getWinner().getName()), true);
        }
    }

    public Player changePlayer(Player player) {
        if (player.equals(players[0])) {
            return players[1];
        } else {
            return players[0];
        }
    }
}
