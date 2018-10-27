package weekopdracht_cafe.Drank;

public class Wijn extends Drankje {
	int inhoudMililiter = 150;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt een " + drank + " geschonken.");
	}

	Wijn(String naam) {
		setAlcoholisch(true);
		setInhoudMililiter(300);

		switch (naam) {
		case "rode wijn":
			setVerkoopprijs(2.55);
			setNaam(naam);
			break;
		case "witte wijn":
			setVerkoopprijs(2.25);
			setNaam(naam);
			break;
		default:
		}
	}
}
