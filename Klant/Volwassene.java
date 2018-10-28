package weekopdracht_cafe.Klant;
import weekopdracht_cafe.Drank.Drankje;
//V0R1
public class Volwassene extends Klant {
	Volwassene(int leeftijd, String naam, int geduldigheid, double geldOpZak, int aantalBestellingenWens){
		super(leeftijd, naam, geduldigheid, geldOpZak, aantalBestellingenWens);
	}
	
	boolean bestellen(Drankje drink) {
		// Nog niet geimplementeerd
		System.out.println("Ik wil graag een " + drink + " bestellen.");
		return true;
	}
}
