package weekopdracht_cafe.Drank;

public class GemixtDrankje extends Drankje implements MetViltje {
	int inhoudMililiter = 330;

	void schenken(Drankje... drinks) {
		for (Drankje d : drinks) {
			// schenk 330 / drinks.size() = ... ml in glas
		}
	}
	
	GemixtDrankje(String naam){
		setAlcoholisch(true);
		setInhoudMililiter(300);
		
		switch(naam) {
		case "gin-tonic":
			setVerkoopprijs(2.95);
			setNaam(naam);
			break;
		case "baco":
			setVerkoopprijs(2.65);
			setNaam(naam);
			break;
		default:
		}
	}
}