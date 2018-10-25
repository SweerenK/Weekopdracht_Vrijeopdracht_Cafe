package weekopdracht_cafe;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import weekopdracht_cafe.Dranken.Drankje;
import weekopdracht_cafe.Klanten.*;

public class Manager{
	Klant klant;
	Drankje drink;
	String[] actiesTegenKlant = {"Vraag naar ID-bewijs", "Vraag of de klant genoeg geld heeft", "Maak drankje", "Weiger drankje", "Zet klant uit de zaak","Bel de NSA-hulplijn"};
	
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

	void overweegActies() {
		Klant klant = Cafe.wachtrij.get(Cafe.wachtrij.size()-1);
		System.out.printf("Jouw inschatting van %s: %s, %s, %s, %s.%n%n",klant.naam, klant.getLeeftijdLabel(klant.leeftijd), klant.getGeduldigheidLabel(klant.geduldigheid),klant.getRijkdomLabel(klant.geldOpZak), klant.getDorstigheidLabel(klant.aantalBestellingenWens));
		System.out.println("Wat wil je doen? ");
		for(int i = 0; i < actiesTegenKlant.length; i++) {
			System.out.println((i+1)+".\t"+actiesTegenKlant[i]);
		}
		try {
			int actieKeuze = Main.scanner.nextInt();
			Main.scanner.nextLine();
			actieNaOverweging(klant, actieKeuze);
		}catch(Exception e) {
			System.out.println("oeps");
		}
		
	}
	
	void actieNaOverweging(Klant klant, int actieKeuze) {
		switch(actieKeuze) {
		case 1:
			int geduldig = (klant.getLeeftijd()>18) ? (klant.getGeduldigheid()-10) : (klant.getGeduldigheid());
			klant.setGeduldigheid(geduldig);
			break;
		case 2:
			//int kanbetalen = (klant.getGeldOpZak() >= klant.drankenWens) ? (klant.getGeduldigheid()) : (klant.getGeduldigheid() - 10);
			//klant.setGeduldigheid(geduldig);
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			toonKlantInfo(klant);
			Main.pressEnter();
			System.out.printf("Het telefoontje duurde zo lang, dat de geduldigheid van %s is afgenomen met 25%%.%n%n", klant.naam);
			klant.setGeduldigheid(klant.getGeduldigheid() - 25);
			overweegActies();
			break;
		default:
		}
	}
	
	void toonKlantInfo(Klant nieuweklant) {
		System.out.printf("\nLeeftijd: %s jaar\tGeduldigheid: %s%%\tGeld op zak: €%.2f\tAantal bestellingen gewenst: %s", nieuweklant.leeftijd, nieuweklant.geduldigheid, nieuweklant.geldOpZak ,nieuweklant.aantalBestellingenWens);
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

	//public Drankje zoekDrankje(String naam) {
		//for(String x : Cafe.drankenlijst) {
			//if(C)
		//}
		//return null;
	//}
	
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

	void checkWachtrij() {
		System.out.printf("%nAantal in wachtrij: %s.", Cafe.wachtrij.size());
	}

	void checkGeduldigheid(int i) {
		System.out.printf("Geduldigheid van %s: %s%%", Cafe.wachtrij.get(i).naam, Cafe.wachtrij.get(i).geduldigheid);
	}

	List<String> toonDrankjes() {
		return Cafe.drankenlijst;
	}
}

class TooYoungForAlcoholException extends Exception {

}

class NotEnoughMoneyException extends Exception {

}

class NoDrinksAvailableException extends Exception {

}