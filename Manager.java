package weekopdracht_cafe;

import java.time.LocalTime;
import java.util.Set;

public class Manager {
	
	
	void inschenken() {

	}
	
	void morsen() {

	}
	
	void verwerkBestelling(Klant klant, Drankje drink) {
		//checkIfAgeAndTime(klant, );
		checkIfKoekje(drink);
		checkIfWarm(drink);
		//checkIfBestellingMogelijk
		//checkIfKoekje
		//checkIfWarm
	}
	
	public boolean checkIfAgeAndTime(Klant klant) {
		if(klant instanceof Jeugd) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkIfWarm(Drankje drink) {
		if(drink instanceof WarmeDrank) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkIfKoekje(Drankje drink) {
		if(drink instanceof MetKoekje) {
			return true;
		}else {
			return false;
		}
	}
	
	void checkTime(Cafe cafe) {
		new Tijd().vorderen(cafe);
	}
	
	void checkWachtrij() {
		System.out.printf("%nAantal in wachtrij: %s.",Cafe.wachtrij.size());
	}
	void checkGeduldigheid(int i) {
		System.out.printf("Geduldigheid van %s: %s%%",Cafe.wachtrij.get(i).naam, Cafe.wachtrij.get(i).geduldigheid);
	}
	
	Set<String> toonDrankjes() {
		return Cafe.drankenkaart.keySet();					// later alfabetisch sorteren
	}
}