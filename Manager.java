package weekopdracht_cafe;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import weekopdracht_cafe.Drank.Drankje;
import weekopdracht_cafe.Klant.*;

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

	void vraagNaarID(Cafe cafe, Klant klant, Drankje drankje) {
		if (checkGeduldigheid(klant.getGeduldigheid())) {
			if(klant.leeftijd<18) {
				cafe.setReputatie(1);
			}
			
			int geduldig = ((klant.getLeeftijd() < 18 && drankje.isAlcoholisch())) ? (klant.getGeduldigheid())
					: (klant.getGeduldigheid() - 10);
			System.out.printf("%s zegt:\t%s", klant.naam, klant.getReactieLeeftijd());
			klant.setGeduldigheid(geduldig);
			Main.pressEnter();
		} else {
			klant.weglopen();
		}
		System.out.println("Debug: " + klant.geduldigheid);
	}

	void vraagOfGenoegGeld(Klant klant, Drankje drankje) {
		if (checkGeduldigheid(klant.getGeduldigheid() - 10)) {
			try {
				checkIfEnoughMoney(klant, drankje);
				klant.setGeduldigheid(klant.getGeduldigheid() - 10);
			} catch (NotEnoughMoneyException neme) {
				System.out.println(getMoneyMessage(klant));
			}
		} else {
			klant.weglopen();
		}
	}

	String getDumpCustomerMessage(Klant klant) {
		klant.aanwezig = false;
		return "Je zet " + klant.naam + " uit de zaak.";
	}
	String getMoneyMessage(Klant klant) {
		klant.aanwezig = false;
		return klant.naam + ":\t\"Oeps ik heb te weinig geld! Doei!\"";
	}

	String getStockMessage(Drankje drankje) {
		return "Dit drankje is niet meer op voorraad: " + drankje.getNaam();
	}

	public void bestellingUitvoeren(Cafe cafe, Klant klant, Drankje drankje, LocalTime time) throws Exception {
		try {
			checkIfEnoughMoney(klant, drankje);
			checkIfTooLateForKids(klant, time);
			checkIfOldEnough(klant, drankje);
			
			int[] dranklocatie = checkIfEnoughStock(cafe, drankje);
			cafe.setDranken(String.valueOf((Integer.parseInt(cafe.getDranken(dranklocatie[0],dranklocatie[1],dranklocatie[2])))-1), dranklocatie[0],  dranklocatie[1],  dranklocatie[2]);
			klant.gedronkenDrankjes.add(drankje);
			klant.aantalGedronkenDrankjes++;
			klant.drankenWens.remove(0);
			if(klant.drankenWens.size() == 0) {
				System.out.println(klant.naam + " drinkt het drankje op en verlaat het café.");
				klant.aanwezig = false;
			}

		} catch (NotEnoughMoneyException neme) {
			System.out.println(getMoneyMessage(klant));
			klant.aantalGeweigerdeDrankjes++;
			Main.pressEnter();
		} catch (NoDrinksAvailableException ndae) {
			System.out.println(getStockMessage(drankje));
			klant.aantalGeweigerdeDrankjes++;
			klant.setGeduldigheid(klant.getGeduldigheid() - 10);
			cafe.setReputatie(-1);
			Main.pressEnter();
		} catch (TooYoungForAlcoholException tyfae) {
			System.out.println("Na 23:00 uur zijn er geen kinderen toegestaan in het café!");
			System.out.println(getDumpCustomerMessage(klant) + "\n");
			cafe.setReputatie(-1);
			Main.pressEnter();
			
		}
	}

	void actieNaOverweging(Cafe cafe, Klant klant, Drankje drankje, LocalTime time) throws Exception {
		try {
			actieKeuze = Main.scanner.nextInt();
			Main.scanner.nextLine();
		} catch (Exception e) {
			System.out.println("oeps");
		}
		System.out.println("Debug: " + klant.geduldigheid);
		switch (actieKeuze) {
		case 1:
			vraagNaarID(cafe, klant, drankje);
			break;
		case 2:
			vraagOfGenoegGeld(klant, drankje);
			break;
		case 3:
			bestellingUitvoeren(cafe, klant, drankje, time);
			break;
		case 4:
			break;
		case 5:
			System.out.println(getDumpCustomerMessage(klant));
			cafe.setReputatie(-3);
			break;
		case 6:
			if (checkGeduldigheid(klant.getGeduldigheid() - 1)) {
				klant.setGeduldigheid(klant.getGeduldigheid() - 1);
				klant.geefInfo();
				System.out.println("Drankenwens: " + klant.drankenWens);
				System.out.println("Gedronken drankjes: " + klant.aantalGedronkenDrankjes + klant.gedronkenDrankjes.toString());
				System.out.println("Geweigerde drankjes: " + klant.aantalGeweigerdeDrankjes);
			
				Main.pressEnter();
			} else {
				klant.weglopen();
			}
			break;
		default:
		}
	}

	public int[] checkIfEnoughStock(Cafe cafe, Drankje drink) throws NoDrinksAvailableException {
		String[][][] dranken = cafe.getDranken();
		for (int i = 0; i < dranken.length; i++) {
			for (int j = 0; j < dranken[i].length; j++) {
				if (dranken[i][j][0].equals(drink.getNaam()) && !dranken[i][j][0].equals("0")) {
					try {
						String voorraad = dranken[i][j][1];
						int voorraadInt = Integer.parseInt(voorraad);
						if (voorraadInt <= 0) {
							throw new NoDrinksAvailableException();
						}else {
							return new int[] {i,j,1};
						}
					} catch (Exception e) {
						System.out.println("Fout bij het controleren van de voorraad.");
					}
				}
			}
		}
		return new int[] {-1,-1,-1};
	}

	public void checkIfEnoughMoney(Klant klant, Drankje drink) throws NotEnoughMoneyException {
		if (klant.geldOpZak < drink.getVerkoopprijs()) {
			throw new NotEnoughMoneyException();
		}
	}

	public void checkIfOldEnough(Klant klant, Drankje drankje) throws TooYoungForAlcoholException {
		if ((klant instanceof Jeugd)&&(drankje.isAlcoholisch())) {
			throw new TooYoungForAlcoholException();
		}
	}
	public void checkIfTooLateForKids(Klant klant, LocalTime tijd) throws TooLateForKidsException {
		if ((klant instanceof Jeugd)&&(tijd.getHour()==23 || tijd.getHour()==0 || tijd.getHour()==1)) {
			throw new TooLateForKidsException();
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

	List<String> toonDrankjes(Cafe cafe) {
		return cafe.getDrankenlijst();
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
class TooLateForKidsException extends Exception {

}