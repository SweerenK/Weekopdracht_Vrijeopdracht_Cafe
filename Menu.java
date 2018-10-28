package weekopdracht_cafe;
//V8R1
public class Menu {
	private String[] hoofdmenuOpties = { "Start spel", "Instellingen", "Stop spel" };
	private String[] instellingsOpties = { "Verander cafénaam", "Admin-mode instellen", "Simulatiesnelheid instellen",
			"Klantdrukte instellen", "Terug naar hoofdmenu" };
	private Cafe cafe;
	private Tijd tijd;

	public void setTijd(Tijd tijd) {
		this.tijd = tijd;
	}

	public void setCafe(Cafe cafe) {
		this.cafe = cafe;
	}

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
			printIntro();
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

	public void printIntro() {
		System.out.println("Welkom in café " + cafe.getNaam()
				+ ".\nWij serveren alleen drankjes.\nHet doel is om elke avond zoveel mogelijk klanten tevreden te stellen.");
		Main.pressEnter();
		System.out.println(
				"We hanteren natuurlijk wel een aantal regels:\n1.\tGeen alcohol onder de 18.\n2.\tNa 23:00 uur is jeugd niet toegestaan in het café.\n3.\tDagelijks geopend van 18:00 - 02:00 uur.\n\nAlles duidelijk? (j / n)");
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
			System.out.println("Hoe wil je het café noemen?");
			cafe.setNaam(Main.scanner.nextLine());
			printInstellingsmenu();
			break;
		case 2:
			if (cafe.isAdminMode()) {
				System.out.println("1.\t[x]Ingeschakeld\n2.\tUitgeschakeld\n");
			} else {
				System.out.println("1.\tIngeschakeld\n2.\t[x]Uitgeschakeld\n");
			}
			System.out.println("Maak je keuze:");
			boolean modus = (Main.scanner.nextInt() == 1) ? true : false;
			Main.scanner.nextLine();
			cafe.setAdminMode(modus);
			printInstellingsmenu();
			break;
		case 3:
			System.out.println("Welke simulatiesnelheid wens je? Maak je keuze:");
			if (tijd.getSnelheid() > 99) {
				System.out.println("1.\tSnel\n2.\tNeutraal\n3.\t[x]Langzaam");
			} else if (tijd.getSnelheid() < 11) {
				System.out.println("1.\t[x]Snel\n2.\tNeutraal\n3.\tLangzaam");
			} else {
				System.out.println("1.\tSnel\n2.\t[x]Neutraal\n3.\tLangzaam");
			}

			int keuze = Main.scanner.nextInt();

			if (keuze == 1) {
				tijd.setSnelheid(10);
			} else if (keuze == 2) {
				tijd.setSnelheid(30);
			} else {
				tijd.setSnelheid(100);
			}
			printInstellingsmenu();
			break;
		case 4:
			System.out.println("Welke klantdrukte wens je? Maak je keuze:");
			if (tijd.getKlantDrukte() < 940) {
				System.out.println("1.\tRelatief rustig\n2.\tNeutraal\n3.\t[x]Relatief druk");
			} else if (tijd.getKlantDrukte() > 970) {
				System.out.println("1.\t[x]Relatief rustig\n2.\tNeutraal\n3.\tRelatief druk");
			} else {
				System.out.println("1.\tRelatief rustig\n2.\t[x]Neutraal\n3.\tRelatief druk");
			}

			int keuzeDrukte = Main.scanner.nextInt();

			if (keuzeDrukte == 1) {
				tijd.setKlantDrukte(979);
			} else if (keuzeDrukte == 2) {
				tijd.setKlantDrukte(939);
			} else {
				tijd.setKlantDrukte(959);
			}
			printInstellingsmenu();
			break;
		case 5:
			printHoofdmenu();
			break;
		}
	}

	public void spelMenu() {
		System.out.println("Maak je keuze: \n1.\t Nieuwe dag (doorspelen)\n2.\t Verkoop café (afsluiten)");
		int keuze = menuListener();
		switch (keuze) {
		case 1:
			cafe.setDagenOpen(cafe.getDagenOpen() + 1);
			break;
		case 2:
			cafe.setInBusiness(false);
			System.out.printf("Na %s dagen verkoop je het café met reputatie %s voor %.2f euro.", cafe.getDagenOpen(),
					cafe.getReputatie(), 999.99);
			break;
		default:
		}
	}
}
