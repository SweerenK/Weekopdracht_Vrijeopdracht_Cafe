package weekopdracht_cafe;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import weekopdracht_cafe.Klant.Klant;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	public static Random random = new Random();
	public static boolean doorspelen = true;

	public static void main(String[] args) throws Exception {
		Cafe cafe = new Cafe();
		Menu menu = new Menu();
		Manager manager = new Manager();
		Tijd tijd = new Tijd();
		menu.setCafe(cafe);
		menu.setTijd(tijd);
		menu.printHoofdmenu();

		while_game: while (cafe.isInBusiness()) {
			cafe.setOpeningstijd(LocalTime.of(18, 0));
			manager.openCafe(tijd, cafe);
			tijd.run(cafe, manager);
			manager.sluitCafe(tijd, cafe);
			pressEnter();
			manager.toonOverzichtDag(cafe);
			pressEnter();

			while_doorspelen: while (cafe.isInBusiness()) {
				try {
					System.out.println("Wil je doorspelen? (j/n)");
					String play = scanner.nextLine();
					if (play.equalsIgnoreCase("j")) {
						tijd.resetDag(tijd, cafe);
						continue while_game;
					} else if (play.equalsIgnoreCase("n")) {
						cafe.setInBusiness(false);
						System.out.printf("Je stopt met spelen. Jouw café met reputatie %s had %.2f euro in de kassa.%n",
								cafe.getReputatie(), cafe.getBedragInKas());
						break while_doorspelen;
					} else {
						System.out.print("Verkeerde invoer. ");
					}

				} catch (IllegalArgumentException wie) {
					System.out.print("Verkeerde invoer. ");
				}
			}
		}
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
		if (checkable.equals("j")) {
			return true;
		} else {
			return false;
		}
	}
}

class wrongInputException extends Exception {

}
