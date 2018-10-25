package weekopdracht_cafe.Dranken;

public class Puur extends Drankje {
	int inhoudMililiter = 150;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt iets anders gebracht.");
	}
	
	Puur(String naam){
		this.setAlcoholisch(true);
		this.setInhoudMililiter(300);
		this.setAantalOpVoorraad(5);
		
		switch(naam) {
		case "Whiskey":
			this.setVerkoopprijs(3.35);
			this.setNaam(naam);
			break;
		case "Cognac":
			this.setVerkoopprijs(3.65);
			this.setNaam(naam);
			break;
		default:
		}
	}
}
