package weekopdracht_cafe.Dranken;

public class Water extends Drankje {
	int inhoudMililiter = 300;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt een " + drank + " geschonken.");
	}

	Water(String naam) {
		this.setAlcoholisch(false);
		this.setInhoudMililiter(300);
		this.setAantalOpVoorraad(999999);
	}
}
