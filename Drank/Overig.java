package weekopdracht_cafe.Drank;

public class Overig extends Drankje implements WarmeDrank, MetKoekje{
	int inhoudMililiter = 150;
	
	public void opwarmen() {
		System.out.println("Je warmt de "+ getNaam() + " op.");
	}

	void schenken(Drankje... drank) {
		System.out.println("Er wordt iets anders gebracht.");
	}
	
	Overig(String naam){
		setAlcoholisch(false);
		setInhoudMililiter(150);
		
		switch(naam) {
		case "thee":
			setVerkoopprijs(1.00);
			setNaam(naam);
			break;
		case "koffie":
			setVerkoopprijs(1.20);
			setNaam(naam);
			break;
		default:
		}
	}
}
