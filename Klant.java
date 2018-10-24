package weekopdracht_cafe;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Klant {
	String naam;
	int leeftijd, geslacht, geduldigheid, aantalBestellingenWens;
	double geldOpZak;
	List<String> drankenWens = new ArrayList<String>(Cafe.drankenkaart.keySet());
	
	Klant(int leeftijd, String naam, int geslacht, int geduldigheid, double geldOpZak, int aantalBestellingenWens){
		this.leeftijd = leeftijd;
		this.naam = naam;
		this.geslacht = geslacht;
		this.geduldigheid = geduldigheid;
		this.geldOpZak = geldOpZak;
		this.aantalBestellingenWens = aantalBestellingenWens;
	}
	//bestelwens

	abstract boolean bestellen(Drankje drink); // IDEE: var args?

	void afrekenen() {
		System.out.println("Het drankje wordt afgerekend.");
	}
	
	boolean checkGenoegGeld(Drankje drink) {
		if(drink.getVerkoopprijs() > geldOpZak) {
			return false;
		}else {
			return true;
		}
	}
	
	void binnenkomen(Klant nieuweklant, LocalTime currentTime) {
		Cafe.klantenlijst.add(nieuweklant);
		genereerWens();
		gaanStaanInWachtrij(nieuweklant);
		System.out.printf("%s komt binnen om %s uur.\nLeeftijd: %s jaar\tGeduldigheid: %s%%\tGeld op zak: €%.2f\tAantal bestellingen gewenst: %s", nieuweklant.naam, currentTime, nieuweklant.leeftijd, nieuweklant.geduldigheid, nieuweklant.geldOpZak ,nieuweklant.aantalBestellingenWens);
	}
	
	void gaanStaanInWachtrij(Klant nieuweklant) {
		Cafe.wachtrij.add(nieuweklant);
	}
	
	void weggaanUitWachtrij(int index) {
		Cafe.wachtrij.remove(index);
	}
	
	void genereerWens() {
		int temp = aantalBestellingenWens;
		drankenWens.clear();
		while(temp > 0) {
			int randomInt = Main.random.nextInt(Cafe.drankenlijst.size());
			drankenWens.add((aantalBestellingenWens - temp),Cafe.drankenlijst.get(randomInt));
			temp--;
		}
		
		System.out.println(this.naam + " wil graag: " + drankenWens.toString());
	}

}

class Volwassene extends Klant {
	Volwassene(int leeftijd, String naam, int geslacht, int geduldigheid, double geldOpZak, int aantalBestellingenWens){
		super(leeftijd, naam, geslacht, geduldigheid, geldOpZak, aantalBestellingenWens);
	}
	
	boolean bestellen(Drankje drink) {
		System.out.println("Ik wil graag een " + drink + " bestellen.");
		return true;
	}
}

class Jeugd extends Klant {
	Jeugd(int leeftijd, String naam, int geslacht, int geduldigheid, double geldOpZak, int aantalBestellingenWens){
		super(leeftijd, naam, geslacht, geduldigheid, geldOpZak, aantalBestellingenWens);
	}
	
	boolean bestellen(Drankje drink) {
		System.out.println("Zou ik een " + drink + " kunnen bestellen?");
		// als te jong, dan return false. Anders true
		return true;
	}

	boolean aanwezigheid(LocalTime tijdstipNu) {
		// als te laat op de dag, return false. Anders true
		return true;
	}
}