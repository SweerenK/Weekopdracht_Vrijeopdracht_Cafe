package weekopdracht_cafe;

import java.util.Random;
import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static Random random = new Random();

	public static void main(String[] args) {
		Cafe cafe = new Cafe();
		Menu menu = new Menu();
		
		menu.setCafe(cafe);
		menu.printHoofdmenu();
		
		Manager manager = new Manager();
		manager.checkTime(cafe);
	}

	public static void pressEnter() {
		String invoer = "invoer";
		loop_while_enter: while (invoer.length() != 0) {
			System.out.println("\n\tDruk op 'enter' om door te gaan..");
			invoer = scanner.nextLine();
			if (invoer.equalsIgnoreCase("q")) {
				System.out.println("No");
				break loop_while_enter;
			}
		}
	}
	
	public static boolean checkYesOrNo(String checkable) {
		if(checkable.equals("j")) {
			return true;
		}else {
			return false;
		}
	}
}
