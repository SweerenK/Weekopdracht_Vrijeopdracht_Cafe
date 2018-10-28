package weekopdracht_cafe;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import weekopdracht_cafe.Klant.Klant;

public class Cafe {
	private String naam = "Drankjewel";
	private String[][][] dranken = {{{"Grolsch","5"},{"Amstel","5"}, {"Heineken","5"}}, {{"rode wijn","5"}, {"witte wijn","5"}}, {{"cognac","5"}, {"whiskey","5"}},
			{{"koffie","25"}, {"thee","25"}}, {{"cola","10"}, {"sinas","10"}, {"7-Up","10"}}, {{"gin-tonic","5"}, {"baco","5"}}};
	private double bedragInKas = 100.00;
	private int reputatie = 50;
	private int dagenOpen = 1;
	private boolean inBusiness = true, adminMode = false;
	private List<Klant> klantenlijst = new ArrayList<Klant>();
	private List<String> drankenlijst = new ArrayList<String>();

	private LocalTime openingstijd = LocalTime.of(18, 0);
	private LocalTime sluitingstijd = LocalTime.of(2, 0);
	
	
	public List<Klant> getKlantenlijst() {
		return klantenlijst;
	}

	public void setKlantenlijst(List<Klant> klantenlijst) {
		this.klantenlijst = klantenlijst;
	}
	public void setDrankenlijst(List<String> drankenlijst) {
		this.drankenlijst = drankenlijst;
	}
	
	public void setDranken(String content, int i, int j, int k) {
		this.dranken[i][j][k] = content;
	}
	
	public String getDranken(int i, int j, int k) {
		return this.dranken[i][j][k];
	}

	public double getBedragInKas() {
		return bedragInKas;
	}

	public void setBedragInKas(double bedragInKas) {
		this.bedragInKas = bedragInKas;
	}

	public int getDagenOpen() {
		return dagenOpen;
	}

	public void setDagenOpen(int dagenOpen) {
		this.dagenOpen = dagenOpen;
	}

	
	
	public boolean isInBusiness() {
		return inBusiness;
	}

	public void setInBusiness(boolean inBusiness) {
		this.inBusiness = inBusiness;
	}

	public boolean isAdminMode() {
		return adminMode;
	}

	public void setAdminMode(boolean adminMode) {
		this.adminMode = adminMode;
	}

	

	public List<String> getDrankenlijst() {
		return drankenlijst;
	}
	
	public String[][][] getDranken() {
		return dranken;
	}
	
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

	public void setReputatie(int i) {
		System.out.println("Wijziging in reputatie café: " +  i + "\nNieuwe reputatie: " + (reputatie+i));
		this.reputatie = reputatie + i;
	}
	
	public Cafe() {
		for (String[][] x : dranken) {
			for(String[] y : x) {
					drankenlijst.add(y[0]);
			}
		}
	}
}