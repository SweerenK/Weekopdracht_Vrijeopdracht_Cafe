package weekopdracht_cafe.Dranken;

import java.util.Arrays;

public class Bier extends Drankje {
	int inhoudMililiter = 330;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt een " + drank + " getapt.");
	}
	
	Bier(String naam){
		this.setAlcoholisch(true);
		this.setInhoudMililiter(300);
		this.setAantalOpVoorraad(5);
		
		switch(naam) {
		case "Grolsch":
			this.setVerkoopprijs(1.30);
			this.setNaam(naam);
			break;
		case "Amstel":
			this.setVerkoopprijs(1.20);
			this.setNaam(naam);
			break;
		case "Heineken":
			this.setVerkoopprijs(1.25);
			this.setNaam(naam);
			break;
		default:
		}
	}
}