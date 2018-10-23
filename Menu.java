package weekopdracht_cafe;

public class Menu {
	String[] hoofdmenuOpties = { "Start spel", "Instellingen", "Stop spel" };
	String[] instellingsOpties = {"Verander cafénaam", "Verander tijdsduur", "Terug naar hoofdmenu"};

	public void printHoofdmenu() {
		System.out.println("Maak je keuze: ");
		for (int i = 0; i < hoofdmenuOpties.length; i++) {
			System.out.println((i + 1) + ".\t" + hoofdmenuOpties[i]);
		}
		hoofdmenuRedirect(menuListener());
	}

	public int menuListener() {
		int menukeuze = Main.scanner.nextInt();
		Main.scanner.nextLine();
		return menukeuze;
	}

	public void hoofdmenuRedirect(int menukeuze) {
		switch (menukeuze) {
		case 1:
			startmenu();
			break;
		case 2:
			printInstellingsmenu();
			break;
		case 3:
			System.out.println("Bedankt voor het spelen!");
			System.exit(1);
			break;
		default:

		}
	}

	public void startmenu() {
		System.out.println(
				"Welkom in café Pubby.\nWij serveren alleen drankjes.\nHet doel is om elke avond zoveel mogelijk klanten tevreden te stellen.");
		Main.pressEnter();
		System.out.println(
				"We hanteren natuurlijk wel een aantal regels:\n1.\tGeen alcohol onder de 18.\n2.\tGeen dronkaards in ons café.\n3.\tDagelijks geopend van 18:00 - 02:00");
		Main.pressEnter();
		System.out.println(
				"Na sluitingstijd maken we de balans op en kijken we wat we kunnen doen om de volgende dag meer omzet te genereren.\n\nAlles duidelijk? (j / n)");

		if (!Main.checkYesOrNo(Main.scanner.nextLine())) {
			printHoofdmenu();
		}
	}
	
	public void printInstellingsmenu() {
		System.out.println("Maak je keuze: ");
		for (int i = 0; i < instellingsOpties.length; i++) {
			System.out.println((i + 1) + ".\t" + instellingsOpties[i]);
		}
		instellingsmenuRedirect(menuListener());
	}
	
	public void instellingsmenuRedirect(int menukeuze) {
		switch (menukeuze) {
		case 1:
			//verander cafenaam
			break;
		case 2:
			//verander tijdsduur (opening, sluiting, snelheid van tijd per seconde)
			break;
		case 3:
			printHoofdmenu();
		default:

		}
	}
	
	
}
