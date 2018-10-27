package weekopdracht_cafe.Klant;

import java.time.LocalTime;

import weekopdracht_cafe.Drank.Drankje;

public class Jeugd extends Klant {
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
