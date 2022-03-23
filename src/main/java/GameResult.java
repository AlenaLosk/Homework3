import com.fasterxml.jackson.annotation.JsonProperty;

public class GameResult {
    @JsonProperty("Player")
    private Player winner;

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

}
