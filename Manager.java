package weekopdracht_cafe;

import java.time.LocalTime;

public class Manager {
	
	
	void inschenken() {

	}
	
	void morsen() {

	}
	
	void verwerkBestelling(Klant klant, Drankje drink) {
		//checkIfBestellingMogelijk
		//checkIfKoekje
		//checkIfWarm
	}
	
	void checkTime(Cafe cafe) {
		new Tijd().vorderen(cafe);
		System.out.println("Het is sluitingstijd. Het café is gesloten.");
	}
}
