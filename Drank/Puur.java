package weekopdracht_cafe.Drank;
//V1R0
public class Puur extends Drankje implements MetViltje{
	int inhoudMililiter = 150;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt iets anders gebracht.");
	}
	
	Puur(String naam){
		setAlcoholisch(true);
		setInhoudMililiter(300);
		
		switch(naam) {
		case "whiskey":
			setVerkoopprijs(3.35);
			setNaam(naam);
			break;
		case "cognac":
			setVerkoopprijs(3.65);
			setNaam(naam);
			break;
		default:
		}
	}
}
