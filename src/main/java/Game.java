import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {
    @JsonProperty("Gameplay")
    private Gameplay gameplay;

    public Game() {
        this.gameplay = new Gameplay();
    }

    public Gameplay getGameplay() {
        return gameplay;
    }
}
