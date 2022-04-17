package tictactoeai;

import java.util.Arrays;

public class TicTacToeBoard {

    public static final char CROSS = 'X';
    public static final char NOUGHT = 'O';
    public static final char EMPTY = ' ';

    private final char[] cells = new char[9];

    public TicTacToeBoard() {
        clearBoard();
    }

    public void setCell(int index, char mark) {
        cells[index] = mark;
    }

    public char getCell(int index) {
        return cells[index];
    }

    public int getTotalMark(char type) {
        int count = 0;
        for (char cell : cells) {
            if (cell == type) count++;
        }
        return count;
    }

    public void clearBoard() {
        Arrays.fill(cells, EMPTY);
    }

    public void display() {
        System.out.println("---------");
        System.out.println("| " + cells[0] + " " + cells[1] + " " + cells[2] + " |");
        System.out.println("| " + cells[3] + " " + cells[4] + " " + cells[5] + " |");
        System.out.println("| " + cells[6] + " " + cells[7] + " " + cells[8] + " |");
        System.out.println("---------");
    }

    public GameState analyse() {
        if (wins(CROSS)) return GameState.CROSS_WINS;
        if (wins(NOUGHT)) return GameState.NOUGHT_WINS;
        if (getTotalMark(EMPTY) == 0) return GameState.DRAW;
        return GameState.NotFinished;
    }

    public boolean wins(char player) {
        if (cells[0] == player && cells[1] == player && cells[2] == player) return true;
        if (cells[3] == player && cells[4] == player && cells[5] == player) return true;
        if (cells[6] == player && cells[7] == player && cells[8] == player) return true;
        if (cells[0] == player && cells[3] == player && cells[6] == player) return true;
        if (cells[1] == player && cells[4] == player && cells[7] == player) return true;
        if (cells[2] == player && cells[5] == player && cells[8] == player) return true;
        if (cells[0] == player && cells[4] == player && cells[8] == player) return true;
        return cells[6] == player && cells[4] == player && cells[2] == player;
    }

    public int[] possibleMoves() {
        int[] moves = new int[getTotalMark(EMPTY)];
        int index = 0;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i] == EMPTY) moves[index++] = i;
        }
        return moves;
    }

    public static int convertCoordinatesToIndex(String x, String y) {
        if ("1".equals(x) && "1".equals(y)) return 0;
        if ("1".equals(x) && "2".equals(y)) return 1;
        if ("1".equals(x) && "3".equals(y)) return 2;
        if ("2".equals(x) && "1".equals(y)) return 3;
        if ("2".equals(x) && "2".equals(y)) return 4;
        if ("2".equals(x) && "3".equals(y)) return 5;
        if ("3".equals(x) && "1".equals(y)) return 6;
        if ("3".equals(x) && "2".equals(y)) return 7;
        if ("3".equals(x) && "3".equals(y)) return 8;

        return -1;
    }
}
