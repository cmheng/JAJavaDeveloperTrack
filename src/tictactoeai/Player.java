package tictactoeai;

public abstract class Player {

    protected Player(char player) {
        this.player = player;
    }

    protected char player;

    public char getPlayer() {
        return player;
    }

    public abstract int move(TicTacToeBoard board);

}
