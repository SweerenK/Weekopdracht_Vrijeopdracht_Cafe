package weekopdracht_cafe.Klant;
//V0R1
import weekopdracht_cafe.Drank.Drankje;

public class Jeugd extends Klant {
	Jeugd(int leeftijd, String naam, int geduldigheid, double geldOpZak, int aantalBestellingenWens){
		super(leeftijd, naam,  geduldigheid, geldOpZak, aantalBestellingenWens);
	}
	
	boolean bestellen(Drankje drink) {
		// Nog niet geimplementeerd
		System.out.println("Zou ik een " + drink + " kunnen bestellen?");
		// als te jong, dan return false. Anders true
		return true;
	}
}
