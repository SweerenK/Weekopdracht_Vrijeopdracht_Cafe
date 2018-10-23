package weekopdracht_cafe;

import java.time.LocalTime;

public abstract class Klant {
	String naam;
	int leeftijd, geslacht, geduldigheid;
	double geldOpZak;
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
	
	
}

class Volwassene extends Klant {
	Volwassene(int leeftijd, String naam, int geslacht, int geduldigheid){
		this.leeftijd = leeftijd;
		this.naam = naam;
		this.geslacht = geslacht;
		this.geduldigheid = geduldigheid;
	}
	
	boolean bestellen(Drankje drink) {
		System.out.println("Ik wil graag een " + drink + " bestellen.");
		return true;
	}
}

class Jeugd extends Klant {
	Jeugd(int leeftijd, String naam, int geslacht, int geduldigheid){
		this.leeftijd = leeftijd;
		this.naam = naam;
		this.geslacht = geslacht;
		this.geduldigheid = geduldigheid;
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
