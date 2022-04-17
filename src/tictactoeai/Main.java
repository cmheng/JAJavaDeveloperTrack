package tictactoeai;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = "";
        while (!"exit".equals(command)) {
            System.out.print("Input command: ");
            String input = scanner.nextLine();
            String[] commands = input.split(" ");
            if ("exit".equals(commands[0])) {
                command = "exit";
                continue;
            }

            if (!"start".equals(commands[0])) {
                System.out.println("Bad parameters!");
                continue;
            } else {
                if (commands.length != 3) {
                    System.out.println("Bad parameters!");
                    continue;
                }
            }

            // Set the players
            Player player1 = new AIPlayerEasy(TicTacToeBoard.CROSS);
            Player player2 = new AIPlayerEasy(TicTacToeBoard.NOUGHT);

            switch (commands[1]) {
                case "user":
                    player1 = new HumanPlayer(TicTacToeBoard.CROSS);
                    ((HumanPlayer) player1).setScanner(scanner);
                    break;
                case "easy":
                    player1 = new AIPlayerEasy(TicTacToeBoard.CROSS);
                    break;
                case "medium":
                    player1 = new AIPlayerMedium(TicTacToeBoard.CROSS);
                    break;
                case "hard":
                    player1 = new AIPlayerHard(TicTacToeBoard.CROSS);
                    break;
            }

            switch (commands[2]) {
                case "user":
                    player2 = new HumanPlayer(TicTacToeBoard.NOUGHT);
                    ((HumanPlayer) player2).setScanner(scanner);
                    break;
                case "easy":
                    player2 = new AIPlayerEasy(TicTacToeBoard.NOUGHT);
                    break;
                case "medium":
                    player2 = new AIPlayerMedium(TicTacToeBoard.NOUGHT);
                    break;
                case "hard":
                    player2 = new AIPlayerHard(TicTacToeBoard.NOUGHT);
                    break;
            }

            // Initialise
            TicTacToeBoard board = new TicTacToeBoard();

            GameState state = GameState.NotFinished;
            boolean isPlayer1 = true;
            while(state == GameState.NotFinished) {

                int targetCell;
                char player;
                if (isPlayer1) {
                    // User move
                    targetCell = player1.move(board);
                    player = player1.getPlayer();
                    isPlayer1 = false;
                } else {
                    targetCell = player2.move(board);
                    player = player2.getPlayer();
                    isPlayer1 = true;
                }

                board.setCell(targetCell, player);
                board.display();

                // Analyze result
                state = board.analyse();
            }

            if (state == GameState.CROSS_WINS) {
                System.out.println("X wins");
            }

            if (state == GameState.NOUGHT_WINS) {
                System.out.println("O wins");
            }

            if (state == GameState.DRAW) {
                System.out.println("Draw");
            }

        }
    }
}
