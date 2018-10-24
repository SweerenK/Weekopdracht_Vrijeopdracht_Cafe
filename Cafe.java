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

public class Cafe {
	private String naam = "Pubby";
	private int reputatie = 50;
	static List<Klant> klantenlijst = new ArrayList<Klant>();
	static List<Klant> wachtrij = new LinkedList<Klant>();
	static HashMap<String, List> drankenkaart = new HashMap<String, List>();
	static ArrayList<String> drankenlijst; 
	
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
	
	Cafe(){
				//Key: Naam drank. Value: [Type, prijs, volume, aantal op voorraad]
				drankenkaart.put("Grolsch", Arrays.asList("Bier","1.30","33","99"));
				drankenkaart.put("Amstel", Arrays.asList("Bier","1.20","33","98"));
				drankenkaart.put("Heineken", Arrays.asList("Bier","1.25","33","97"));
				drankenkaart.put("Baco", Arrays.asList("Mix","2.65","33","89"));
				drankenkaart.put("Gin Tonic", Arrays.asList("Mix","2.95","33","88"));
				drankenkaart.put("Rode wijn", Arrays.asList("Wijn","2.25","33","79"));
				drankenkaart.put("Witte wijn", Arrays.asList("Wijn","2.55","33","78"));
				drankenkaart.put("Cola", Arrays.asList("Frisdrank","1.30","33","69"));
				drankenkaart.put("Sinas", Arrays.asList("Frisdrank","1.30","33","68"));
				drankenkaart.put("7-Up", Arrays.asList("Frisdrank","1.30","33","67"));
				drankenkaart.put("Whiskey", Arrays.asList("Puur","3.35","33","59"));
				drankenkaart.put("Cognac", Arrays.asList("Puur","3.65","33","58"));
				
				drankenlijst = new ArrayList<String>(drankenkaart.keySet());
	}
}