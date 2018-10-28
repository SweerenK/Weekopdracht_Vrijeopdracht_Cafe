package weekopdracht_cafe.Drank;
//V1R0
public class Water extends Drankje {
	int inhoudMililiter = 300;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt een " + drank + " geschonken.");
	}

	Water(String naam) {
		setAlcoholisch(false);
		setInhoudMililiter(300);
	}
}
