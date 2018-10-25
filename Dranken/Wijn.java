package weekopdracht_cafe.Dranken;

public class Wijn extends Drankje {
	int inhoudMililiter = 150;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt een " + drank + " geschonken.");
	}

	Wijn(String naam) {
		this.setAlcoholisch(true);
		this.setInhoudMililiter(300);
		this.setAantalOpVoorraad(5);

		switch (naam) {
		case "Rode wijn":
			this.setVerkoopprijs(2.55);
			this.setNaam(naam);
			break;
		case "Witte wijn":
			this.setVerkoopprijs(2.25);
			this.setNaam(naam);
			break;
		default:
		}
	}
}
