package weekopdracht_cafe;

import java.util.Random;
import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	public static Random random = new Random();
	
	public static void main(String[] args) {
		Cafe cafe = new Cafe();
		Menu menu = new Menu();
		Manager manager = new Manager();
		Tijd tijd = new Tijd();
		
		menu.setCafe(cafe);
		menu.printHoofdmenu();
		tijd.run(manager, cafe);
		
		//while(cafe.getSluitingstijd().isAfter(cafe.getCurrentTime))
		checkTime(cafe, manager);
	}
	static void checkTime(Cafe cafe, Manager manager) {
		new Tijd().run();
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
