package weekopdracht_cafe.Klanten;
import weekopdracht_cafe.Dranken.Drankje;

public class Volwassene extends Klant {
	Volwassene(int leeftijd, String naam, int geslacht, int geduldigheid, double geldOpZak, int aantalBestellingenWens){
		super(leeftijd, naam, geslacht, geduldigheid, geldOpZak, aantalBestellingenWens);
	}
	
	boolean bestellen(Drankje drink) {
		System.out.println("Ik wil graag een " + drink + " bestellen.");
		return true;
	}
}
