package cinema;

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) { 

        Scanner scanner = new Scanner(System.in);    
        
        char[][] seats = initCinema(scanner);
        displayCinema(seats);

        int choice = -1;

        while (choice != 0) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayCinema(seats);
                    break;
                case 2:
                    buyTicket(seats, scanner);
                    break;
                case 3:
                    showStatistics(seats);
                    break;
            }
        }       
    }

    private static void showStatistics(char[][] seats) {
        int purchasedTickets = 0;
        int currentIncome = 0;
        int totalIncome = 0;

        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                if (seats[i][j] == 'B') {
                    purchasedTickets++;
                    currentIncome += calculateTicketPrice(seats, i);
                }
                totalIncome += calculateTicketPrice(seats, i);
            }
        }

        int totalSeats = seats.length * seats[0].length;
        double percent = (double) purchasedTickets / totalSeats * 100;

        System.out.printf("%nNumber of purchased tickets: %d%n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%%n", percent);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", totalIncome);
    }

    private static char[][] initCinema(Scanner scanner) {
        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");        
        int seatsPerRow = scanner.nextInt();
        char[][] seats = new char[rows][seatsPerRow];
        for (int i = 0; i < seats.length; i++) {
            for (int j = 0; j < seats[0].length; j++) {
                seats[i][j] = 'S';
            }
        }
        return seats;
    }

    private static void displayCinema(char[][] seats) {
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 0; i < seats[0].length; i++) {
            System.out.print(i + 1);
            System.out.print(" ");
        }
        System.out.println();
        
        for (int i = 0; i < seats.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < seats[0].length; j++) {
                System.out.print(" " + seats[i][j]);
            }
            System.out.println();
        }
    }

    private static int calculateTicketPrice(char[][] seats, int rowIndex) { 
        int totalSeats = seats.length * seats[0].length;

        if (totalSeats <= 60 || rowIndex < seats.length / 2) {
            return 10;
        } else {
            return 8;
        }
    }

    private static void buyTicket(char[][] seats, Scanner scanner) {

        while (true) {
            System.out.println("\nEnter a row number:");
            int rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();

            if (rowNumber < 1 || rowNumber > seats.length || seatNumber < 1 || seatNumber > seats[0].length) {
                System.out.println("Wrong input!");
                continue;
            }

            if (seats[rowNumber - 1][seatNumber - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
                continue;
            } 

            seats[rowNumber - 1][seatNumber - 1] = 'B';    
            System.out.println("\nTicket price: $" + calculateTicketPrice(seats, rowNumber - 1));
            break;
        }

        
    }
    
}