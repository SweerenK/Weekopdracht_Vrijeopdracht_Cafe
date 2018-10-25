package weekopdracht_cafe.Dranken;

public class Frisdrank extends Drankje {
	int inhoudMililiter = 200;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt een glaasje " + drank + " gebracht.");
	}
	
	Frisdrank(String naam){
		this.setAlcoholisch(false);
		this.setInhoudMililiter(300);
		this.setAantalOpVoorraad(10);
		
		switch(naam) {
		case "Cola":
			this.setVerkoopprijs(1.20);
			this.setNaam(naam);
			break;
		case "Sinas":
			this.setVerkoopprijs(1.25);
			this.setNaam(naam);
			break;
		case "7-Up":
			this.setVerkoopprijs(1.30);
			this.setNaam(naam);
			break;
		default:
		}
	}
}