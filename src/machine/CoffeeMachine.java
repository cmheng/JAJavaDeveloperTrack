package machine;

import java.util.Scanner;

public class CoffeeMachine {
		
	private static final int WATER_PER_ESPRESSO = 250;
	private static final int COFFEE_BEANS_PER_ESPRESSO = 16;
	private static final int COST_PER_ESPRESSO = 4;

	private static final int WATER_PER_LATTE = 350;
	private static final int MILK_PER_LATTE = 75;
	private static final int COFFEE_BEANS_PER_LATTE = 20;
	private static final int COST_PER_LATTE = 7;

	private static final int WATER_PER_CAPPU = 200;
	private static final int MILK_PER_CAPPU = 100;
	private static final int COFFEE_BEANS_PER_CAPPU = 12;
	private static final int COST_PER_CAPPU = 6;
	
	private static final int INITIAL_WATER = 400;
	private static final int INITIAL_MILK = 540;
	private static final int INITIAL_BEANS = 120;
	private static final int INITIAL_CUPS = 9;
	private static final int INITIAL_MONEY = 550;
	
	private int waterInMachine = INITIAL_WATER;
	private int milkInMachine = INITIAL_MILK;
	private int beansInMachine = INITIAL_BEANS;
	private int cupsInMachine = INITIAL_CUPS;
	private int moneyInMachine = INITIAL_MONEY;	
	
	private InputState inputState = InputState.ACTION; 
	
	public void input(String command) {
		switch (inputState) {
			case ACTION:				
				switch (command) {
					case "buy":
						inputState = InputState.BUY;
						break;
					case "fill":
						inputState = InputState.ADD_WATER;
						break;
					case "take":					
						System.out.printf("I gave you $%d%n", moneyInMachine);
						moneyInMachine = 0;
						break;
					case "remaining":	
						System.out.println("\nThe coffee machine has:");
						System.out.printf("%d ml of water%n", waterInMachine);
						System.out.printf("%d ml of milk%n", milkInMachine);
						System.out.printf("%d g of coffee beans%n", beansInMachine);
						System.out.printf("%d of disposable cups%n", cupsInMachine);
						System.out.printf("$%d of money%n", moneyInMachine);
						break;
					case "exit":
						inputState = InputState.EXIT;
				}
				break;
			
			case BUY:
				switch (command) {
				case "1": 
					buyCoffee(CoffeeType.ESPRESSO);
					break;
				case "2": 
					buyCoffee(CoffeeType.LATTE);
					break;
				case "3": 
					buyCoffee(CoffeeType.CAPPUCCINO);
					break;
				case "back":
					break;
			}
			inputState = InputState.ACTION;
			break;
			
			case ADD_WATER:
				int water = Integer.parseInt(command);
				waterInMachine += water;
				inputState = InputState.ADD_MILK;
				break;
			case ADD_MILK:
				int milk = Integer.parseInt(command);
				milkInMachine += milk;
				inputState = InputState.ADD_BEANS;
				break;
			case ADD_BEANS:
				int beans = Integer.parseInt(command);
				beansInMachine += beans;
				inputState = InputState.ADD_CUP;
				break;
			case ADD_CUP:
				int cup = Integer.parseInt(command);
				cupsInMachine += cup;
				inputState = InputState.ACTION;
				break;
		}
	}
	
	private void buyCoffee(CoffeeType coffeeType) {
		int water = 0;
		int milk = 0;
		int beans = 0;		
		int cost = 0;
		
		switch (coffeeType) {
			case ESPRESSO:
				water = WATER_PER_ESPRESSO;
				beans= COFFEE_BEANS_PER_ESPRESSO;
				cost = COST_PER_ESPRESSO;
				break;
			case LATTE:
				water = WATER_PER_LATTE;
				milk = MILK_PER_LATTE;
				beans= COFFEE_BEANS_PER_LATTE;
				cost = COST_PER_LATTE;
				break;
			case CAPPUCCINO:
				water = WATER_PER_CAPPU;
				milk = MILK_PER_CAPPU;
				beans= COFFEE_BEANS_PER_CAPPU;
				cost = COST_PER_CAPPU;
				break;
		}
		
		if (waterInMachine < water) {
			System.out.println("Sorry, not enough water!");
			return;
		}
		if (milkInMachine < milk) {
			System.out.println("Sorry, not enough milk!");
			return;
		}
		if (beansInMachine < beans) {
			System.out.println("Sorry, not enough coffee beans!");
			return;
		}
		if (cupsInMachine == 0) {
			System.out.println("Sorry, not enough disposable cups!");
			return;
		}
		System.out.println("I have enough resources, making you a coffee!");
		waterInMachine -= water;
		milkInMachine -= milk;
		beansInMachine -= beans;
		moneyInMachine += cost;
		cupsInMachine--;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);		
		
		CoffeeMachine coffeeMachine = new CoffeeMachine();

		boolean exit = false;
		while (!exit) {
			switch (coffeeMachine.inputState) {
				case ACTION:
					System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
					coffeeMachine.input(scanner.nextLine());
					break;
				case BUY:
					System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
					coffeeMachine.input(scanner.nextLine());
					break;
				case ADD_WATER:
					System.out.println("Write how many ml of water you want to add:");
					coffeeMachine.input(scanner.nextLine());
					break;
				case ADD_MILK:
					System.out.println("Write how many ml of milk you want to add:");
					coffeeMachine.input(scanner.nextLine());
					break;
				case ADD_BEANS:
					System.out.println("Write how many grams of coffee beans you want to add:");
					coffeeMachine.input(scanner.nextLine());
					break;
				case ADD_CUP:
					System.out.println("Write how many disposable cups of coffee you want to add:");
					coffeeMachine.input(scanner.nextLine());
					break;
				case EXIT:
					exit = true;
					break;
			}			
		}
	}
}

enum InputState {
	ACTION, BUY, ADD_WATER, ADD_MILK, ADD_BEANS, ADD_CUP, EXIT
}

enum CoffeeType {
	ESPRESSO, LATTE, CAPPUCCINO
}