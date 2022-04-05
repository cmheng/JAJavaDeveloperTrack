package tictactoe;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
				
		char[][] grid = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				grid[i][j] = ' ';
			}
		}
		
		display(grid);
		
		boolean isGameOver = false;
		char token = 'X';
		while (!isGameOver) {
			boolean isValidInput = false;
			while (!isValidInput) {
				System.out.print("Enter the coordinates: ");
				String x = scanner.next().trim();
				String y = scanner.nextLine().trim();
				
				isValidInput = updateGrid(grid, x, y, token);
			}		
			token = token == 'X' ? 'O' : 'X'; 
			display(grid);
			String state = analyse(grid);
			if (state.equals("X wins") || state.equals("O wins") || state.equals("Draw")) {
				System.out.println(state);
				isGameOver = true;
			}
		}
		scanner.close();
	}
	
	private static boolean updateGrid(char[][] grid, String x, String y, char token) {
		int xNum, yNum;
		try {
			xNum = Integer.parseInt(x);
			yNum = Integer.parseInt(y);
		} catch (NumberFormatException e) {
			System.out.println("You should enter numbers!");
			return false;
		}
		
		if (xNum <1 || xNum > 3 || yNum <1 || yNum > 3) {
			System.out.println("Coordinates should be from 1 to 3!");
			return false;
		}
		
		if (grid[xNum - 1][yNum - 1] == 'X' || grid[xNum - 1][yNum - 1] == 'O') {
			System.out.println("This cell is occupied! Choose another one!");
			return false;
		}
		
		grid[xNum - 1][yNum - 1] = token;
		return true;
		
	}

	private static String analyse(char[][] grid) {
		
		if (has3InRow(grid, 'X') && has3InRow(grid, 'O')) {
			return "Impossible";
		}
		
		if (Math.abs(numberOfTokens(grid, 'X') - numberOfTokens(grid, 'O')) > 1) {
			return "Impossible";
		}
		
		if (has3InRow(grid, 'X')) {
			return "X wins";
		}
		
		if (has3InRow(grid, 'O')) {
			return "O wins";
		}
		
		if (numberOfTokens(grid, ' ') == 0) {
			return "Draw";
		}
		
		return "Game not finished";
		
	}	
	
	private static boolean has3InRow(char[][] grid, char token) {
		if (grid[0][0] == (token) && grid[0][1] == (token) && grid[0][2] == (token))
			return true;
		if (grid[1][0] == (token) && grid[1][1] == (token) && grid[1][2] == (token))
			return true;
		if (grid[2][0] == (token) && grid[2][1] == (token) && grid[2][2] == (token))
			return true;
		if (grid[0][0] == (token) && grid[1][0] == (token) && grid[2][0] == (token))
			return true;
		if (grid[0][1] == (token) && grid[1][1] == (token) && grid[2][1] == (token))
			return true;
		if (grid[0][2] == (token) && grid[1][2] == (token) && grid[2][2] == (token))
			return true;
		if (grid[0][0] == (token) && grid[1][1] == (token) && grid[2][2] == (token))
			return true;
		if (grid[0][2] == (token) && grid[1][1] == (token) && grid[2][0] == (token))
			return true;
		
		return false;
	}
	
	private static int numberOfTokens(char[][] grid, char token) {
		int count = 0;
		for (char[] row : grid) {
			for (char c : row) {
				if (c == token) count++;
			}
		}
		return count;
	}

	private static void display(char[][] grid) {
		System.out.println("---------");		
		for (char[] row : grid) {
			System.out.print("| ");
			for (char c : row) {
				System.out.print(c);
				System.out.print(" ");
			}
			System.out.println("|");
		}
		System.out.println("---------");				
	}

}
