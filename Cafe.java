package weekopdracht_cafe;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.event.ListSelectionEvent;

import weekopdracht_cafe.Klanten.Klant;

public class Cafe {
	private String naam = "Pubby";
	private String[][] dranken = { {"Grolsch", "Amstel", "Heineken"}, {"Rode wijn", "Witte wijn"}, {"Cognac", "Whiskey"},
			{"Koffie", "Thee"}, {"Cola", "Sinas", "7-Up"}, {"Gin-Tonic", "Baco"} };
	private int reputatie = 50;
	
	public static List<Klant> klantenlijst = new ArrayList<Klant>();
	public static List<Klant> wachtrij = new LinkedList<Klant>();
	public static List<String> drankenlijst = new ArrayList<String>();

	private LocalTime openingstijd = LocalTime.of(18, 0);
	private LocalTime sluitingstijd = LocalTime.of(2, 0);

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

	Cafe() {
		for (String[] x : dranken) {
			for(String y : x) {
				drankenlijst.add(y);
			}
		}
	}
}