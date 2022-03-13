public class Step {
    private int num;
    private int playerId;
    private int cell;

    public Step(int num, int playerId, int cell) {
        this.num = num;
        this.playerId = playerId;
        this.cell = cell;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
