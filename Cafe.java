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

import weekopdracht_cafe.Klant.Klant;

public class Cafe {
	private String naam = "Pubby";
	private String[][][] dranken = {{{"Grolsch","5"},{"Amstel","5"}, {"Heineken","5"}}, {{"rode wijn","5"}, {"witte wijn","5"}}, {{"cognac","5"}, {"whiskey","5"}},
			{{"koffie","25"}, {"thee","25"}}, {{"cola","10"}, {"sinas","10"}, {"7-Up","10"}}, {{"gin-tonic","5"}, {"baco","5"}}};

	public void setDranken(String content, int i, int j, int k) {
		this.dranken[i][j][k] = content;
	}
	
	public String getDranken(int i, int j, int k) {
		return this.dranken[i][j][k];
	}

	private double bedragInKas = 100.00;
	public double getBedragInKas() {
		return bedragInKas;
	}

	public void setBedragInKas(double bedragInKas) {
		this.bedragInKas = bedragInKas;
	}

	private int reputatie = 50;
	public int dagenOpen = 1;
	public boolean inBusiness = true, adminMode = false;
	
	public boolean isAdminMode() {
		return adminMode;
	}

	public void setAdminMode(boolean adminMode) {
		this.adminMode = adminMode;
	}

	public static List<Klant> klantenlijst = new ArrayList<Klant>();
	public List<String> drankenlijst = new ArrayList<String>();

	private LocalTime openingstijd = LocalTime.of(18, 0);
	private LocalTime sluitingstijd = LocalTime.of(2, 0);

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