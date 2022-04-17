package tictactoeai;

import java.util.Random;

public class AIPlayerMedium extends Player {


    protected AIPlayerMedium(char player) {
        super(player);
    }

    @Override
    public int move(TicTacToeBoard board) {
        System.out.println("Making move level \"medium\"");
        int[] moves = board.possibleMoves();

        // If it already has two in a row and can win with one further move, it does so.
        for (int moveIndex : moves) {
            char p = player;
            board.setCell(moveIndex, p);
            if (board.wins(p)) {
                board.setCell(moveIndex, TicTacToeBoard.EMPTY);
                return moveIndex;
            }
            board.setCell(moveIndex, TicTacToeBoard.EMPTY);
        }

        // If its opponent can win with one move, it plays the move necessary to block this.
        for (int moveIndex : moves) {
            char p = player == TicTacToeBoard.CROSS ? TicTacToeBoard.NOUGHT : TicTacToeBoard.CROSS;
            board.setCell(moveIndex, p);
            if (board.wins(p)) {
                board.setCell(moveIndex, TicTacToeBoard.EMPTY);
                return moveIndex;
            }
            board.setCell(moveIndex, TicTacToeBoard.EMPTY);
        }

        // Else make random move
        Random random = new Random();
        int index = random.nextInt(moves.length);
        return moves[index];
    }
}
