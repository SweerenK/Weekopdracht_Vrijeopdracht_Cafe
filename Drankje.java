package weekopdracht_cafe;

public abstract class Drankje {
	private String naam;
	private Double verkoopprijs;
	private int inhoudMililiter;
	private boolean alcoholisch;

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Double getVerkoopprijs() {
		return verkoopprijs;
	}

	public void setVerkoopprijs(Double verkoopprijs) {
		this.verkoopprijs = verkoopprijs;
	}

	public int getInhoudMililiter() {
		return inhoudMililiter;
	}

	public void setInhoudMililiter(int inhoudMililiter) {
		this.inhoudMililiter = inhoudMililiter;
	}

	public boolean isAlcoholisch() {
		return alcoholisch;
	}

	public void setAlcoholisch(boolean alcoholisch) {
		this.alcoholisch = alcoholisch;
	}

	//niet-getter/setter methodes:
	abstract void schenken(Drankje... d);
}

class GemixtDrankje extends Drankje {
	int inhoudMililiter = 330;

	void schenken(Drankje... drinks) {
		for (Drankje d : drinks) {
			// schenk 330 / drinks.size() = ... ml in glas
		}
	}
}

class Bier extends Drankje {
	int inhoudMililiter = 330;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt een " + drank + " getapt.");
	}
}

class Wijn extends Drankje {
	int inhoudMililiter = 150;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt een " + drank + " geschonken.");
	}
}

class Frisdrank extends Drankje {
	int inhoudMililiter = 200;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt een glaasje " + drank + " gebracht.");
	}
}

class OverigPuur extends Drankje {
	int inhoudMililiter = 150;

	void schenken(Drankje... drank) {
		System.out.println("Er wordt iets anders gebracht.");
	}
}





//Getters en Setters
