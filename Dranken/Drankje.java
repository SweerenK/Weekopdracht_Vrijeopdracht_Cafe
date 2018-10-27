package weekopdracht_cafe.Dranken;

public abstract class Drankje {
	private String naam;
	private Double verkoopprijs;
	private int inhoudMililiter, aantalOpVoorraad;
	private boolean alcoholisch;

	public int getAantalOpVoorraad() {
		return aantalOpVoorraad;
	}

	public void setAantalOpVoorraad(int aantalOpVoorraad) {
		this.aantalOpVoorraad = aantalOpVoorraad;
	}

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



//Getters en Setters
