package weekopdracht_cafe.Dranken;

public class GemixtDrankje extends Drankje {
	int inhoudMililiter = 330;

	void schenken(Drankje... drinks) {
		for (Drankje d : drinks) {
			// schenk 330 / drinks.size() = ... ml in glas
		}
	}
	
	GemixtDrankje(String naam){
		this.setAlcoholisch(true);
		this.setInhoudMililiter(300);
		this.setAantalOpVoorraad(5);
		
		switch(naam) {
		case "Gin tonic":
			this.setVerkoopprijs(2.95);
			this.setNaam(naam);
			break;
		case "Baco":
			this.setVerkoopprijs(2.65);
			this.setNaam(naam);
			break;
		default:
		}
	}
}