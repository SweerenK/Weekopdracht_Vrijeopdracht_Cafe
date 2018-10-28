package weekopdracht_cafe;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import weekopdracht_cafe.Drank.Drankje;
import weekopdracht_cafe.Drank.MetKoekje;
import weekopdracht_cafe.Drank.MetRietje;
import weekopdracht_cafe.Drank.MetViltje;
import weekopdracht_cafe.Drank.WarmeDrank;
import weekopdracht_cafe.Klant.*;

public class Manager extends Thread {
	private Klant klant;
	private Drankje drink;
	private String[] actiesTegenKlant = { "Vraag naar ID-bewijs", "Vraag of de klant genoeg geld heeft", "Maak/Geef drankje",
			"Weiger drankje", "Zet klant uit de zaak", "Vraag de klant om alle informatie (admin-modus)" };
	private String[] redenenWeigering = { "Je hebt niet genoeg geld.", "We hebben het niet op voorraad.",
			"Je bent te jong om dit nu te bestellen.", "Ik heb een hekel aan jou." };
	private int actieKeuze;

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

	void overweegActies(Klant klant, Cafe cafe) {
		if (klant.isAanwezig()) {
			inschattenKlant(klant);
			if (klant.isAanwezig()) {
				System.out.printf("Er is €%.2f in de kassa. Wat wil je doen?%n", cafe.getBedragInKas());
				for (int i = 0; i < actiesTegenKlant.length - 1; i++) {
					System.out.println((i + 1) + ".\t" + actiesTegenKlant[i]);
				}
				if (cafe.isAdminMode())
					System.out
							.println((actiesTegenKlant.length + ".\t" + actiesTegenKlant[actiesTegenKlant.length - 1]));
			}
		}
	}

	public void maakRedenenWeigeringKenbaar(Klant klant, Drankje drankje) {
		if (klant.isAanwezig()) {
			System.out.printf("Waarom wil je %s een %s weigeren?%n", klant.getNaam(), drankje.getNaam());
			for (int j = 0; j < redenenWeigering.length; j++) {
				System.out.println((j + 1) + ".\t" + redenenWeigering[j]);
			}
		}
	}

	public boolean controleRedenWeigering(int reden, Cafe cafe, Klant klant, Drankje drankje, LocalTime time)
			throws Exception {

		try {
			checkCorrectDrinkDeniedByAgeAndTime(klant, drankje, time);
			checkCorrectDrinkDeniedByMoney(klant, drankje);
			checkCorrectDrinkDeniedByStock(cafe, drankje);
		} catch (NotEnoughMoneyException neme) {
			if (reden == 1) {
				klant.reactieOpGeweigerdDrankjeEens();
				return true;
			} else {
				klant.reactieOpGeweigerdDrankjeOneens();
				cafe.setReputatie(-1);
				klant.setGeduldigheid(klant.getGeduldigheid() - 20);
				return false;
			}
		} catch (NoDrinksAvailableException ndae) {
			if (reden == 2) {
				klant.reactieOpGeweigerdDrankjeEens();
				return true;
			} else {
				klant.reactieOpGeweigerdDrankjeOneens();
				cafe.setReputatie(-1);
				klant.setGeduldigheid(klant.getGeduldigheid() - 15);
				return false;
			}
		} catch (TooYoungForAlcoholException | TooLateForKidsException kidsException) {
			if (reden == 3) {
				klant.reactieOpGeweigerdDrankjeEens();
				return true;
			} else {
				klant.reactieOpGeweigerdDrankjeOneens();
				cafe.setReputatie(-1);
				klant.setGeduldigheid(klant.getGeduldigheid() - 15);
				return false;
			}
		}

		if (reden == 4) {
			System.out.println(klant.reactieOpOnbeschofteManager(klant));
			klant.setGeduldigheid(0);
			weigerDrankjes(klant, klant.getDrankenWens());
			cafe.setReputatie(-5);
			klant.setAanwezig(false);
		}
		return false;
	}
	
	void weigerDrankjes(Klant klant, List<String>...drankjes) {
		while(klant.getDrankenWens().size() != 0) {
			List<String> drankenwens = klant.getDrankenWens();
			drankenwens.remove(0);
			klant.setDrankenWens(drankenwens);
			klant.setAantalGeweigerdeDrankjes(klant.getAantalGeweigerdeDrankjes() + 1);
		}
	}

	void inschattenKlant(Klant klant) {
		if (klant.isAanwezig()) {
			System.out.printf("%nJouw inschatting van %s:\t\t\t%s, %s, %s, %s.%n%n", klant.getNaam(),
					klant.getLeeftijdLabel(klant.getLeeftijd()), klant.getGeduldigheidLabel(klant.getGeduldigheid()),
					klant.getRijkdomLabel(klant.getGeldOpZak()), klant.getDorstigheidLabel(klant.getAantalBestellingenWens()));
		}
	}

	void vraagNaarID(Cafe cafe, Klant klant, Drankje drankje) {
		if (checkGeduldigheid(klant.getGeduldigheid())) {
			System.out.printf("%n%s is %s jaar oud.%n", klant.getNaam(), klant.getLeeftijd());
			if (klant.getLeeftijd() < 18 && !klant.isCheckedID()) {
				cafe.setReputatie(1);
				klant.setCheckedID(true);
			}

			int geduldig = ((klant.getLeeftijd() < 18 && drankje.isAlcoholisch())) ? (klant.getGeduldigheid() - 5)
					: (klant.getGeduldigheid() - 10);
			System.out.printf("%s zegt:\t%s", klant.getNaam(), klant.getReactieLeeftijd());
			klant.setGeduldigheid(geduldig);
			Main.pressEnter();
		} else {
			klant.weglopen();
		}
	}

	void vraagOfGenoegGeld(Klant klant, Drankje drankje) {
		if (checkGeduldigheid(klant.getGeduldigheid() - 10)) {
			try {
				checkIfEnoughMoney(klant, drankje);
				System.out
						.println(klant.getNaam() + ":\t\"Ja, ik heb genoeg geld voor een " + drankje.getNaam() + ".\"");
				klant.setGeduldigheid(klant.getGeduldigheid() - 10);
			} catch (NotEnoughMoneyException neme) {
				System.out.println(getMoneyMessage(klant));
				Main.pressEnter();
			}
		} else {
			klant.weglopen();
		}
	}

	String getDumpCustomerMessage(Klant klant) {
		klant.setAanwezig(false);;
		return "Je zet " + klant.getNaam() + " uit de zaak.";
	}

	String getMoneyMessage(Klant klant) {
		klant.setAanwezig(false);
		return klant.getNaam() + ":\t\"Oeps ik heb te weinig geld! Doei!\"";
	}

	String getStockMessage(Drankje drankje) {
		return "Dit drankje is niet meer op voorraad: " + drankje.getNaam();
	}

	public boolean checkCorrectDrinkDeniedByMoney(Klant klant, Drankje drankje) throws Exception {
		try {
			checkIfEnoughMoney(klant, drankje);
			return false;
		} catch (NotEnoughMoneyException neme) {
			return true;
		}
	}

	public boolean checkCorrectDrinkDeniedByStock(Cafe cafe, Drankje drankje) throws Exception {
		try {
			checkIfEnoughStock(cafe, drankje);
			return false;
		} catch (NoDrinksAvailableException neme) {
			return true;
		}
	}

	public boolean checkCorrectDrinkDeniedByAgeAndTime(Klant klant, Drankje drankje, LocalTime tijd) throws Exception {
		try {
			checkIfOldEnough(klant, drankje);
			checkIfTooLateForKids(klant, tijd);
			return false;
		} catch (TooLateForKidsException | TooYoungForAlcoholException kidsException) {
			return true;
		}
	}

	public boolean checkBestellingMogelijk(Cafe cafe, Klant klant, Drankje drankje, LocalTime time) throws Exception {
		try {
			checkIfEnoughMoney(klant, drankje);
			checkIfTooLateForKids(klant, time);
			checkIfOldEnough(klant, drankje);

			int[] dranklocatie = checkIfEnoughStock(cafe, drankje);
			cafe.setDranken(
					String.valueOf(
							(Integer.parseInt(cafe.getDranken(dranklocatie[0], dranklocatie[1], dranklocatie[2]))) - 1),
					dranklocatie[0], dranklocatie[1], dranklocatie[2]);

		} catch (NotEnoughMoneyException neme) {
			System.out.println(getMoneyMessage(klant));
			klant.setAantalGeweigerdeDrankjes(klant.getAantalGeweigerdeDrankjes()+1);
			klant.setDrankenWens(klant.getDrankenWens().subList(1, klant.getDrankenWens().size()));
			klant.setAantalBestellingenWens(klant.getDrankenWens().size());
			Main.pressEnter();
			return false;
		} catch (NoDrinksAvailableException ndae) {
			System.out.println(getStockMessage(drankje));
			klant.setAantalGeweigerdeDrankjes(klant.getAantalGeweigerdeDrankjes()+1);
			klant.setDrankenWens(klant.getDrankenWens().subList(1, klant.getDrankenWens().size()));
			klant.setAantalBestellingenWens(klant.getDrankenWens().size());
			klant.setGeduldigheid(klant.getGeduldigheid() - 10);
			cafe.setReputatie(-2);
			Main.pressEnter();
			return false;
		} catch (TooYoungForAlcoholException tyfae) {
			System.out.println("Kinderen mogen geen alcohol in ons café!");
			klant.setDrankenWens(klant.getDrankenWens().subList(1, klant.getDrankenWens().size()));
			klant.setAantalBestellingenWens(klant.getDrankenWens().size());
			System.out.println(getDumpCustomerMessage(klant) + "\n");
			cafe.setReputatie(-3);
			Main.pressEnter();
			return false;
		} catch (TooLateForKidsException tlfke) {
			System.out.println("Na 23:00 uur zijn er geen kinderen toegestaan in het café!");
			klant.setAantalGeweigerdeDrankjes(klant.getAantalBestellingenWens());
			weigerDrankjes(klant, klant.getDrankenWens());
			System.out.println(getDumpCustomerMessage(klant) + "\n");
			cafe.setReputatie(-1);
			Main.pressEnter();
			return false;
		}
		return true;
	}

	public void extraServicesBijDrankje(Drankje drankje) {
		if(checkIfWarm(drankje)) {
			WarmeDrank drink = (WarmeDrank)drankje;
			drink.opwarmen();
			drink.roeren();
		}
		if(checkIfKoekje(drankje)) {
			MetKoekje drink = (MetKoekje)drankje;
			drink.voegKoekjeToe();
		}
		if(checkIfRietje(drankje)) {
			MetRietje drink = (MetRietje)drankje;
			drink.voegRietjeToe();
		}
		if(checkIfViltje(drankje)) {
			MetViltje drink = (MetViltje)drankje;
			drink.voegViltjeToe();
		}
	}
	
	public void bestellingUitvoeren(Cafe cafe, Klant klant, Drankje drankje, LocalTime time) throws Exception {
		if (checkBestellingMogelijk(cafe, klant, drankje, time) && klant.isAanwezig()) {
			klant.afrekenen(drankje);
			extraServicesBijDrankje(drankje);
			cafe.setBedragInKas(cafe.getBedragInKas() + drankje.getVerkoopprijs());
			List<Drankje> templist = klant.getGedronkenDrankjes();
			templist.add(drankje);
			klant.setGedronkenDrankjes(templist);
			klant.setAantalGedronkenDrankjes(klant.getAantalGedronkenDrankjes()+1);
			klant.setDrankenWens(klant.getDrankenWens().subList(1, klant.getDrankenWens().size()));
			klant.setGeldOpZak(klant.getGeldOpZak() - drankje.getVerkoopprijs());
			klant.setAantalBestellingenWens(klant.getAantalBestellingenWens() - 1);

			if (klant.getDrankenWens().size() == 0) {
				System.out.println(klant.getNaam() + " drinkt het drankje op en verlaat het café.");
				klant.setAanwezig(false);
				Main.pressEnter();
			} else {
				klant.maakWensKenbaar();
			}
		}
	}

	void toonOverzichtDag(Cafe cafe) {
		System.out.println("OVERZICHT DAG " + cafe.getDagenOpen());
		System.out.println("Klant\tAge\tTijd\tBesteed\tGeweigerd\tGedronken drankjes");
		for(int i = 0; i < cafe.getKlantenlijst().size(); i++) {
			Klant klant = cafe.getKlantenlijst().get(i);
			double besteedBedrag = 0.00;
			for(int j = 0; j< klant.getGedronkenDrankjes().size();j++) {
				besteedBedrag = besteedBedrag + klant.getGedronkenDrankjes().get(j).getVerkoopprijs();
			}
			System.out.printf("%s\t%s\t%s\t%.2f\t%s\t\t%s%n", klant.getNaam(), klant.getLeeftijd(), klant.getBinnenkomst(), besteedBedrag, klant.getAantalGeweigerdeDrankjes(), klant.getGedronkenDrankjes().toString());
		}
	}
	void actieNaOverweging(Cafe cafe, Klant klant, Drankje drankje, LocalTime time) throws Exception {
		if (klant.isAanwezig()) {
			try {
				actieKeuze = Main.scanner.nextInt();
				Main.scanner.nextLine();
			} catch (Exception e) {
				System.out.println("oeps");
			}
			
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
				if (!checkCorrectDrinkDeniedByAgeAndTime(klant, drankje, time)
						&& !checkCorrectDrinkDeniedByMoney(klant, drankje)
						&& !checkCorrectDrinkDeniedByStock(cafe, drankje)) {
					System.out.println(klant.getNaam() + ":\t" + klant.reactieOpGeweigerdDrankjeOneens());
					klant.setGeduldigheid(klant.getGeduldigheid() - 30);
					cafe.setReputatie(-1);
					klant.setAantalGeweigerdeDrankjes(klant.getAantalGeweigerdeDrankjes() + 1);
					klant.setDrankenWens(klant.getDrankenWens().subList(1, klant.getDrankenWens().size()));
					klant.setAantalBestellingenWens(klant.getDrankenWens().size());
					
				} else {
					System.out.println(klant.getNaam() + ":\t" + klant.reactieOpGeweigerdDrankje(drankje));
					maakRedenenWeigeringKenbaar(klant, drankje);
					int reden = Main.scanner.nextInt();
					switch (reden) {
					case 1:
						if (checkCorrectDrinkDeniedByMoney(klant, drankje)) {
							System.out.println(klant.getNaam() + ":\t" + klant.reactieOpGeweigerdDrankjeEens());
							klant.setGeduldigheid(klant.getGeduldigheid() - 5);
							cafe.setReputatie(1);
							klant.setDrankenWens(klant.getDrankenWens().subList(1, klant.getDrankenWens().size()));
							klant.setAantalGeweigerdeDrankjes(klant.getAantalGeweigerdeDrankjes() + 1);
							klant.setAantalBestellingenWens(klant.getDrankenWens().size());
						} else {
							System.out.println(klant.getNaam() + ":\t" + klant.reactieOpGeweigerdDrankjeOneens());
							klant.setGeduldigheid(klant.getGeduldigheid() - 10);
							cafe.setReputatie(-1);
						}
						break;
					case 2:
						if (checkCorrectDrinkDeniedByStock(cafe, drankje)) {
							System.out.println(klant.getNaam() + ":\t" + klant.reactieOpGeweigerdDrankjeEens());
							klant.setDrankenWens(klant.getDrankenWens().subList(1, klant.getDrankenWens().size()));
							klant.setAantalGeweigerdeDrankjes(klant.getAantalGeweigerdeDrankjes() + 1);
							klant.setAantalBestellingenWens(klant.getDrankenWens().size());
						} else {
							System.out.println(klant.getNaam() + ":\t" + klant.reactieOpGeweigerdDrankjeOneens());
							klant.setGeduldigheid(klant.getGeduldigheid() - 10);
							cafe.setReputatie(-1);
						}
						break;
					case 3:
						if (checkCorrectDrinkDeniedByAgeAndTime(klant, drankje, time)) {
							System.out.println(klant.getNaam() + ":\t" + klant.reactieOpGeweigerdDrankjeEens());
							cafe.setReputatie(2);
							klant.setDrankenWens(klant.getDrankenWens().subList(1, klant.getDrankenWens().size()));
							klant.setAantalGeweigerdeDrankjes(klant.getAantalGeweigerdeDrankjes() + 1);
							klant.setAantalBestellingenWens(klant.getDrankenWens().size());
						} else {
							System.out.println(klant.getNaam() + ":\t" + klant.reactieOpGeweigerdDrankjeOneens());
							klant.setGeduldigheid(klant.getGeduldigheid() - 10);
							cafe.setReputatie(-1);
						}
						break;
					case 4:
						System.out.println(klant.getNaam() + ":\t" + klant.reactieOpOnbeschofteManager(klant));
						cafe.setReputatie(-5);
						break;
					}
				}
				if(klant.getGeduldigheid()<=0) {
					klant.setAanwezig(false);
					klant.weglopen();
				}

				break;
			case 5:
				System.out.println(getDumpCustomerMessage(klant));
				cafe.setReputatie(-3);
				break;
			case 6:
				if (cafe.isAdminMode()) {
					if (checkGeduldigheid(klant.getGeduldigheid())) {
						klant.setGeduldigheid(klant.getGeduldigheid());
						klant.geefInfo();
						System.out.println("\nDrankenwens: " + klant.getDrankenWens());
						System.out.println("Gedronken drankjes: " + klant.getAantalGedronkenDrankjes());
						System.out.println("Geweigerde drankjes: " + klant.getAantalGeweigerdeDrankjes());

						Main.pressEnter();
					} else {
						klant.weglopen();
					}
				}
				break;
			default:
			}
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
						} else {
							return new int[] { i, j, 1 };
						}
					} catch (Exception e) {
						System.out.println("Fout bij het controleren van de voorraad.");
					}
				}
			}
		}
		return new int[] { -1, -1, -1 };
	}

	public void checkIfEnoughMoney(Klant klant, Drankje drink) throws NotEnoughMoneyException {
		if (klant.getGeldOpZak() < drink.getVerkoopprijs()) {
			throw new NotEnoughMoneyException();
		}
	}

	public void checkIfOldEnough(Klant klant, Drankje drankje) throws TooYoungForAlcoholException {
		if ((klant instanceof Jeugd) && (drankje.isAlcoholisch())) {
			throw new TooYoungForAlcoholException();
		}
	}

	public void checkIfTooLateForKids(Klant klant, LocalTime tijd) throws TooLateForKidsException {
		if ((klant instanceof Jeugd) && (tijd.getHour() == 23 || tijd.getHour() == 0 || tijd.getHour() == 1)) {
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
	
	public boolean checkIfRietje(Drankje drink) {
		if (drink instanceof MetRietje) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean checkIfViltje(Drankje drink) {
		if (drink instanceof MetViltje) {
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