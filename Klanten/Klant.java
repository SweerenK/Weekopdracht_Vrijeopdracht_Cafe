package weekopdracht_cafe.Klanten;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import weekopdracht_cafe.*;
import weekopdracht_cafe.Dranken.Drankje;

public abstract class Klant {
	public String naam;
	public int leeftijd, geslacht, geduldigheid, aantalBestellingenWens;
	public double geldOpZak;
	public List<String> drankenWens;
	
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
	
	public int getLeeftijd() {
		return leeftijd;
	}

	public void setLeeftijd(int leeftijd) {
		this.leeftijd = leeftijd;
	}

	public int getAantalBestellingenWens() {
		return aantalBestellingenWens;
	}

	public void setAantalBestellingenWens(int aantalBestellingenWens) {
		this.aantalBestellingenWens = aantalBestellingenWens;
	}

	public double getGeldOpZak() {
		return geldOpZak;
	}

	public void setGeldOpZak(double geldOpZak) {
		this.geldOpZak = geldOpZak;
	}
	
	public int getGeduldigheid() {
		return geduldigheid;
	}

	public void setGeduldigheid(int geduldigheid) {
		this.geduldigheid = geduldigheid;
	}
	
	public String getLeeftijdLabel(int leeftijd) {
		if(leeftijd < 22) {
			return "redelijk jong";
		}else if(leeftijd > 60) {
			return "redelijk oud";
		}else {
			return "volwassen";
		}
	}
	
	public String getGeduldigheidLabel(int geduldigheid) {
		if(geduldigheid < 20) {
			return "erg ongeduldig";
		}else if(geduldigheid < 40) {
			return "ongeduldig";
		}else if(geduldigheid < 60){
			return "geduldig";
		}else {
			return "erg geduldig";
		}
	}
	
	public String getRijkdomLabel(double geldOpZak) {
		if(geldOpZak*aantalBestellingenWens < 2.00*aantalBestellingenWens) {
			return "redelijk arm";
		}else if(geldOpZak/aantalBestellingenWens > 4.00) {
			return "redelijk rijk";
		}else {
			return "onduidelijk hoe rijk";
		}
	}
	
	public String getDorstigheidLabel(int aantalBestellingenWens) {
		if(aantalBestellingenWens <= 2) {
			return "niet zo dorstig";
		}else if(aantalBestellingenWens >=4) {
			return "dorstig";
		}else {
			return "onduidelijk hoeveel dorst";
		}
	}
	
	boolean checkGenoegGeld(Drankje drink) {
		if(drink.getVerkoopprijs() > geldOpZak) {
			return false;
		}else {
			return true;
		}
	}
	
	public void binnenkomen(Klant nieuweklant, LocalTime currentTime) {
		drankenWens = new ArrayList<String>(Cafe.drankenlijst);
		Cafe.klantenlijst.add(nieuweklant);
		System.out.printf("%s komt binnen om %s uur", nieuweklant.naam, currentTime);
		//\nLeeftijd: %s jaar\tGeduldigheid: %s%%\tGeld op zak: €%.2f\tAantal bestellingen gewenst: %s", nieuweklant.naam, currentTime, nieuweklant.leeftijd, nieuweklant.geduldigheid, nieuweklant.geldOpZak ,nieuweklant.aantalBestellingenWens
		genereerWens();
		gaanStaanInWachtrij(nieuweklant);
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
		
		System.out.println(" en wil graag: " + drankenWens.toString());
	}
}