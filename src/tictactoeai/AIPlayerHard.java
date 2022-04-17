package tictactoeai;

public class AIPlayerHard extends Player {

    private final char opPlayer;

    protected AIPlayerHard(char player) {
        super(player);
        this.opPlayer = this.player == TicTacToeBoard.CROSS ? TicTacToeBoard.NOUGHT : TicTacToeBoard.CROSS;
    }

    @Override
    public int move(TicTacToeBoard board) {
        System.out.println("Making move level \"hard\"");

        Move move = minimax(board, this.player);
        return move.getIndex();
    }

    private Move minimax(TicTacToeBoard board, char player) {

        //available spots
        int[] availSpots = board.possibleMoves();

        // checks for the terminal states such as win, lose, and tie
        //and returning a value accordingly
        if (board.wins(this.player)) {
            Move move = new Move();
            move.setScore(10);
            return move;
        }

        if (board.wins(this.opPlayer)) {
            Move move = new Move();
            move.setScore(-10);
            return move;
        }

        if (availSpots.length == 0) {
            Move move = new Move();
            move.setScore(0);
            return move;
        }

        // an array to collect all the moves
        Move[] moves = new Move[availSpots.length];

        // loop through available spots
        for (int i = 0; i < availSpots.length; i++) {
            Move move = new Move();
            move.setIndex(availSpots[i]);
            board.setCell(move.getIndex(), player);

            Move result;
            if (player == this.player) {
                result = minimax(board, this.opPlayer);
            } else {
                result = minimax(board, this.player);
            }
            move.setScore(result.getScore());

            board.setCell(move.getIndex(), TicTacToeBoard.EMPTY);

            moves[i] = move;
        }

        int bestMove = 0;
        if (player == this.player) {
            int bestScore = -10000;
            for (int i = 0; i < moves.length; i++) {
                if (moves[i].getScore() > bestScore) {
                    bestScore = moves[i].getScore();
                    bestMove = i;
                }
            }
        } else {
            int bestScore = 10000;
            for (int i = 0; i < moves.length; i++) {
                if (moves[i].getScore() < bestScore) {
                    bestScore = moves[i].getScore();
                    bestMove = i;
                }
            }
        }
        return moves[bestMove];
    }
}

class Move {
    int index;
    int score;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
