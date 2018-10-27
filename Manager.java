package weekopdracht_cafe;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import weekopdracht_cafe.Dranken.Drankje;
import weekopdracht_cafe.Klanten.*;

public class Manager extends Thread {
	Klant klant;
	Drankje drink;
	String[] actiesTegenKlant = { "Vraag naar ID-bewijs", "Vraag of de klant genoeg geld heeft", "Maak/Geef drankje",
			"Weiger drankje", "Zet klant uit de zaak", "Vraag de klant om alle informatie" };
	int actieKeuze;

	public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	public Drankje getDrink() {
		return drink;
	}

	public void setDrink(Drankje drink) {
		this.drink = drink;
	}

	void inschenken() {
	}

	void morsen() {
	}

	void overweegActies(Klant klant) {
		inschattenKlant(klant);
		if (klant.aanwezig) {
			System.out.println("Wat wil je doen? ");
			for (int i = 0; i < actiesTegenKlant.length; i++) {
				System.out.println((i + 1) + ".\t" + actiesTegenKlant[i]);
			}
		}
	}

	void inschattenKlant(Klant klant) {
		if (klant.aanwezig) {
			System.out.printf("%nJouw inschatting van %s:\t\t\t%s, %s, %s, %s.%n%n", klant.naam,
					klant.getLeeftijdLabel(klant.leeftijd), klant.getGeduldigheidLabel(klant.geduldigheid),
					klant.getRijkdomLabel(klant.geldOpZak), klant.getDorstigheidLabel(klant.aantalBestellingenWens));
		}
	}

	void actieNaOverweging(Klant klant,Drankje drankje) {
		try {
			actieKeuze = Main.scanner.nextInt();
			Main.scanner.nextLine();
		} catch (Exception e) {
			System.out.println("oeps");
		}
		System.out.println("Debug: "+klant.geduldigheid);
		switch (actieKeuze) {
		case 1:
			if (checkGeduldigheid(klant.getGeduldigheid())) {
				int geduldig = ((klant.getLeeftijd() < 18 && drankje.isAlcoholisch())) ? (klant.getGeduldigheid()) : (klant.getGeduldigheid()-10);
			System.out.printf("%s zegt:\t%s", klant.naam, klant.getReactieLeeftijd());
			klant.setGeduldigheid(geduldig);
			Main.pressEnter();
			}else {
				klant.weglopen();
			}
			System.out.println("Debug: "+klant.geduldigheid);
			break;
		case 2:
			// int kanbetalen = (klant.getGeldOpZak() >= klant.drankenWens) ?
			// (klant.getGeduldigheid()) : (klant.getGeduldigheid() - 10);
			// klant.setGeduldigheid(geduldig);
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			if (checkGeduldigheid(klant.getGeduldigheid() - 25)) {
				klant.setGeduldigheid(klant.getGeduldigheid() - 25);
				klant.geefInfo();
				Main.pressEnter();
			} else {
				klant.weglopen();
			}
			break;
		default:
		}
	}

	void verwerkBestelling(Tijd tijd, Klant klant, Drankje drink) {
		try {
			checkIfOldEnough(klant);
			checkIfBestellingMogelijk(klant, drink);
			if (checkIfKoekje(drink)) {
				System.out.println(klant.naam + " krijgt een koekje."); // TODOinterface aanroepen
			}
			if (checkIfWarm(drink)) {
				System.out.println("Het drankje wordt geroerd."); // TODOinterface aanroepen
			}

		} catch (TooYoungForAlcoholException tyfae) {
			System.out.println(klant.naam + " is te jong voor alcohol.");
		} catch (NotEnoughMoneyException neme) {
			System.out.println(klant.naam + " heeft te weinig geld voor dit drankje.");
		} catch (NoDrinksAvailableException ndae) {
			System.out.println("Dit drankje is niet meer op voorraad: " + drink.getNaam());
		}

	}

	public void checkIfBestellingMogelijk(Klant klant, Drankje drink)
			throws NotEnoughMoneyException, NoDrinksAvailableException {
		if (drink.getAantalOpVoorraad() == 0) {
			throw new NoDrinksAvailableException();
		}
		if (klant.geldOpZak < drink.getVerkoopprijs()) {
			throw new NotEnoughMoneyException();
		}
	}

	public void checkIfOldEnough(Klant klant) throws TooYoungForAlcoholException {
		if (klant instanceof Jeugd) {
			throw new TooYoungForAlcoholException();
		}
	}

	public boolean checkIfWarm(Drankje drink) {
		if (drink instanceof WarmeDrank) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkIfKoekje(Drankje drink) {
		if (drink instanceof MetKoekje) {
			return true;
		} else {
			return false;
		}
	}

	boolean checkGeduldigheid(int i) {
		boolean geduldig = (i <= 0) ? false : true;
		return geduldig;
	}

	List<String> toonDrankjes() {
		return Cafe.drankenlijst;
	}
	
	void openCafe(Tijd tijd, Cafe cafe) {
		System.out.println("De zaak opent om " + tijd.getIngameTijd(cafe) + " uur. We wachten op de eerste klant...");
	}

	void sluitCafe(Tijd tijd, Cafe cafe) {
		System.out.println("De zaak sluit om " + tijd.getIngameTijd(cafe) + " uur. Tijd om de balans op te maken.");
	}
}



class TooYoungForAlcoholException extends Exception {

}

class NotEnoughMoneyException extends Exception {

}

class NoDrinksAvailableException extends Exception {

}