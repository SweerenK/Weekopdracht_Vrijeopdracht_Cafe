package weekopdracht_cafe.Dranken;

public class Overig extends Drankje{
	int inhoudMililiter = 150;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt iets anders gebracht.");
	}
	
	Overig(String naam){
		this.setAlcoholisch(false);
		this.setInhoudMililiter(150);
		this.setAantalOpVoorraad(10);
		
		switch(naam) {
		case "Thee":
			this.setVerkoopprijs(1.00);
			this.setNaam(naam);
			break;
		case "Koffie":
			this.setVerkoopprijs(1.20);
			this.setNaam(naam);
			break;
		default:
		}
	}
}
