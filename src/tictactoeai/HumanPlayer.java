package tictactoeai;

import java.util.Scanner;

public class HumanPlayer extends Player {

    Scanner scanner;

    protected HumanPlayer(char player) {
        super(player);
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int move(TicTacToeBoard board) {
        board.display();
        int targetCell;
        while (true) {
            System.out.print("Enter the coordinates: ");
            String x = scanner.next().trim();
            String y = scanner.nextLine().trim();

            // validate input
            try {
                Integer.parseInt(x);
                Integer.parseInt(y);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            targetCell = TicTacToeBoard.convertCoordinatesToIndex(x, y);
            if (targetCell == -1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (board.getCell(targetCell) != TicTacToeBoard.EMPTY) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            break;
        }
        return targetCell;
    }
}
