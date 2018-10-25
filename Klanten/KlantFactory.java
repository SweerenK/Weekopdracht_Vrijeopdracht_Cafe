package weekopdracht_cafe.Klanten;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import weekopdracht_cafe.*;

public class KlantFactory {
	String[][] namenlijst = {
			{ "Ali", "Bernard", "Charles", "David", "Edward", "Fernando", "Gerrit", "Henk", "Iwan", "James", "Karel",
					"Leo", "Menno", "Nick" },
			{ "Anne", "Bea", "Claire", "Daisy", "Emma", "Fenna", "Gemma", "Hannah", "Ilse", "Jennie", "Karin", "Maaike",
					"Naomi" } };
	Random random = new Random();

	public Klant KlantFactory(LocalTime tijd) {
		int randomKlant = random.nextInt(10);
		int geslacht = random.nextInt(2);
		String naam = namenlijst[geslacht][random.nextInt(namenlijst[0].length - 1)];
		int aantalBestellingenWens = random.nextInt(5) + 1;
		double geldOpZak = ((double) 100 * (random.nextInt(20) + 1) + (double) random.nextInt(100)) / 100;
		int geduldigheid;
		List<String> bestellingenWens = new ArrayList<String>(Cafe.drankenlijst);

		if (randomKlant < 2) {
			geduldigheid = random.nextInt(66) + 10;
		} else {
			geduldigheid = random.nextInt(91) + 10;
		}

		return (randomKlant < 2)
				? new Jeugd(random.nextInt(9) + 8, naam, geslacht, geduldigheid, geldOpZak, aantalBestellingenWens)
				: new Volwassene(random.nextInt(70) + 18, naam, geslacht, geduldigheid, geldOpZak,
						aantalBestellingenWens);
	}

	public void GenereerBezoekendeKlant(LocalTime huidig, Cafe cafe) {
		int uurOffset = cafe.getSluitingstijd().getHour() + 1;
		while (huidig.minusHours(uurOffset).isBefore(cafe.getSluitingstijd().minusHours(uurOffset))) {
			if (Main.random.nextInt(100) > 98) {
				Klant nieuweklant = new KlantFactory().KlantFactory(huidig);
				nieuweklant.binnenkomen(nieuweklant, huidig);
			}
			if (huidig.getMinute() == 0) {
				System.out.println("Het is nu " + huidig + " uur.");
			}
			Main.pressEnter();
			// manager.verwerkBestelling(nieuweklant, nieuweklant.drankenWens.get(0));

		}
	}
}