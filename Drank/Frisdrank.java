package weekopdracht_cafe.Drank;

public class Frisdrank extends Drankje implements MetRietje, MetViltje {
	int inhoudMililiter = 200;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt een glaasje " + drank + " gebracht.");
	}
	
	Frisdrank(String naam){
		setAlcoholisch(false);
		setInhoudMililiter(300);
		
		switch(naam) {
		case "cola":
			setVerkoopprijs(1.20);
			setNaam(naam);
			break;
		case "sinas":
			setVerkoopprijs(1.25);
			setNaam(naam);
			break;
		case "7-Up":
			setVerkoopprijs(1.30);
			setNaam(naam);
			break;
		default:
		}
	}
}