package weekopdracht_cafe;

import java.time.LocalTime;
import java.util.ArrayList;

public class Cafe {
	private String naam;
	private int reputatie = 50;
	static ArrayList<Klant> klantenlijst = new ArrayList<Klant>();
	
	private LocalTime openingstijd = LocalTime.of(18,0);
	private LocalTime sluitingstijd = LocalTime.of(2,0);
	
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public LocalTime getOpeningstijd() {
		return openingstijd;
	}
	public void setOpeningstijd(LocalTime openingstijd) {
		this.openingstijd = openingstijd;
	}
	public LocalTime getSluitingstijd() {
		return sluitingstijd;
	}
	public void setSluitingstijd(LocalTime sluitingstijd) {
		this.sluitingstijd = sluitingstijd;
	}
	public int getReputatie() {
		return reputatie;
	}
	public void setReputatie(int reputatie) {
		this.reputatie = reputatie;
	}

}
