package weekopdracht_cafe.Klant;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import weekopdracht_cafe.*;
import weekopdracht_cafe.Drank.Drankje;

public abstract class Klant {
	public String naam;
	public int leeftijd, geslacht, geduldigheid, aantalBestellingenWens, aantalGedronkenDrankjes,
			aantalGeweigerdeDrankjes;
	public double geldOpZak;
	public List<String> drankenWens;
	public List<Drankje> gedronkenDrankjes;
	public boolean aanwezig = true;
	public LocalTime binnenkomst;

	public String[] blijeReactie = { " is blij", " is gelukkig", " glimlacht", " is tevreden", " lijkt dankbaar",
			" is verheugd" };
	public String[] nietBlijeReactie = { " lijkt geirriteerd", " lijkt geergerd", " reageert humeurig", " moppert",
			" reageert chagrijnig", " zucht" };
	public String[] wegloopReactie = { " en loopt weg.", " en verlaat het café.", " en vertrekt uit het café." };
	public String[][][] reactieLeeftijd = { { { "\"Knijp gewoon een oogje dicht..\"", "\"Wat een slechte zaak\"" },
			{ "\"Flauw hoor..\"", "\"Jammer dit..\"" }, { "\"Sorry, ik ben inderdaad te jong\"" }, { "\"Helaas..\"" } },
			{ { "\"Geef me gewoon mijn drankje!\"", "\"Wat doe je nou moeilijk?!\"", "\"Schiet op!\"" },
					{ "\"Ik ben inderdaad volwassen.\"", "\"Zie je wel?\"" },
					{ "\"Ik snap dat je dit moest vragen.\"", "\"Geen probleem.\"" } } };

	public Klant() {
		gedronkenDrankjes = new ArrayList<Drankje>();
	}

	Klant(int leeftijd, String naam, int geslacht, int geduldigheid, double geldOpZak, int aantalBestellingenWens) {
		this.leeftijd = leeftijd;
		this.naam = naam;
		this.geslacht = geslacht;
		this.geduldigheid = geduldigheid;
		this.geldOpZak = geldOpZak;
		this.aantalBestellingenWens = aantalBestellingenWens;
	}
	// bestelwens

	abstract boolean bestellen(Drankje drink); // IDEE: var args?

	void afrekenen() {
		System.out.println("Het drankje wordt afgerekend.");
	}

	public int getLeeftijd() {
		return leeftijd;
	}

	public void setLeeftijd(int leeftijd) {
		this.leeftijd = leeftijd;
	}

	public int getAantalBestellingenWens() {
		return aantalBestellingenWens;
	}

	public void setAantalBestellingenWens(int aantalBestellingenWens) {
		this.aantalBestellingenWens = aantalBestellingenWens;
	}

	public double getGeldOpZak() {
		return geldOpZak;
	}

	public void setGeldOpZak(double geldOpZak) {
		this.geldOpZak = geldOpZak;
	}

	public int getGeduldigheid() {
		return geduldigheid;
	}

	public void setGeduldigheid(int geduldigheid) {
		this.geduldigheid = geduldigheid;
	}

	public String getLeeftijdLabel(int leeftijd) {
		if (leeftijd < 22) {
			return "redelijk jong";
		} else if (leeftijd > 60) {
			return "redelijk oud";
		} else {
			return "volwassen";
		}
	}

	public String getGeduldigheidLabel(int geduldigheid) {
		if (geduldigheid < 20) {
			return "erg ongeduldig";
		} else if (geduldigheid < 40) {
			return "ongeduldig";
		} else if (geduldigheid < 60) {
			return "geduldig";
		} else {
			return "erg geduldig";
		}
	}

	public String getRijkdomLabel(double geldOpZak) {
		if (geldOpZak * aantalBestellingenWens < 2.00 * aantalBestellingenWens) {
			return "redelijk arm";
		} else if (geldOpZak / aantalBestellingenWens > 4.00) {
			return "redelijk rijk";
		} else {
			return "onduidelijk hoe rijk";
		}
	}

	public String getDorstigheidLabel(int aantalBestellingenWens) {
		if (aantalBestellingenWens <= 2) {
			return "niet zo dorstig";
		} else if (aantalBestellingenWens >= 4) {
			return "dorstig";
		} else {
			return "onduidelijk hoeveel dorst";
		}
	}

	boolean checkGenoegGeld(Drankje drink) {
		if (drink.getVerkoopprijs() > geldOpZak) {
			return false;
		} else {
			return true;
		}
	}

	public void binnenkomen(Cafe cafe, Klant nieuweklant, LocalTime currentTime) {
		binnenkomst = currentTime;
		drankenWens = new ArrayList<String>(cafe.drankenlijst);
		Cafe.klantenlijst.add(nieuweklant);
		System.out.printf("%n%s komt binnen om %s uur.", nieuweklant.naam, currentTime);
	}

	public void genereerWens(Cafe cafe) {
		int temp = aantalBestellingenWens;
		drankenWens.clear();

		while (temp > 0) {
			int randomInt = Main.random.nextInt(cafe.getDrankenlijst().size());
			drankenWens.add((aantalBestellingenWens - temp), cafe.getDrankenlijst().get(randomInt));
			temp--;
		}

		System.out.printf("%n%s vraagt: \t\"Heb je voor mij een %s?\"%n",naam,
				drankenWens.get(aantalGedronkenDrankjes + aantalGeweigerdeDrankjes).toString());
	}

	public String getReactieLeeftijd() {
		int geduld = (geduldigheid > 55) ? 2 : ((geduldigheid > 30) ? 1 : 0);
		int age = (leeftijd < 17) ? 0 : 1;
		return reactieLeeftijd[age][geduld][Main.random.nextInt(reactieLeeftijd[age][geduld].length)];
	}

	public void geefInfo() {
		System.out.printf("%s:\t\"Moet dat nou echt? Nou, vooruit dan maar..\"%n", naam);
		System.out.printf("Leeftijd: %s jaar\tGeduldigheid: %s%%\tGeld op zak: €%.2f\tAantal bestellingen gewenst: %s",
				leeftijd, geduldigheid, geldOpZak, aantalBestellingenWens);
	}

	public void weglopen() {
		System.out.print(naam + nietBlijeReactie[Main.random.nextInt(nietBlijeReactie.length)]
				+ wegloopReactie[Main.random.nextInt(wegloopReactie.length)]);
		System.out.println(" Even geduld, terwijl we wachten op de volgende klant...");
		aanwezig = false;
	}
}