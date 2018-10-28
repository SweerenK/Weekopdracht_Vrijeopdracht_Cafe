package weekopdracht_cafe.Drank;

import java.util.Arrays;

public class Bier extends Drankje implements MetViltje{
	int inhoudMililiter = 330;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt een " + drank + " getapt.");
	}
	
	Bier(String naam){
		setAlcoholisch(true);
		setInhoudMililiter(300);
		
		switch(naam) {
		case "Grolsch":
			setVerkoopprijs(1.30);
			setNaam(naam);
			break;
		case "Amstel":
			setVerkoopprijs(1.20);
			setNaam(naam);
			break;
		case "Heineken":
			setVerkoopprijs(1.25);
			setNaam(naam);
			break;
		default:
		}
	}
}