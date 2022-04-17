package tictactoeai;

import java.util.Random;

public class AIPlayerEasy extends Player {

    protected AIPlayerEasy(char player) {
        super(player);
    }

    @Override
    public int move(TicTacToeBoard board) {
        System.out.println("Making move level \"easy\"");
        int[] moves = board.possibleMoves();
        Random random = new Random();
        int index = random.nextInt(moves.length);
        return moves[index];
    }
}
