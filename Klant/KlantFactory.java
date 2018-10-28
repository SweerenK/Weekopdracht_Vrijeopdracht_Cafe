package weekopdracht_cafe.Klant;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import weekopdracht_cafe.*;

public class KlantFactory {
	String[][] namenlijst = {
			{ "Ali", "Arno", "Albert", "Bernie", "Ben", "Bob", "Charles", "David", "Dirk", "Danny", "Edward", "Eduart", "Ernst", "Fred", "Frits", "Gerrit", "Henk", "Harrie", "Herman", "James","Jim","Jeff", "Karel", "Kevin",
					"Leo", "Lennart","Louis", "Nick", "Peter", "Pieter", "Rick", "Rodrick", "Remco", "Stefan", "Tim", "Tom", "Vincent", "Willem", "Wim" },
			{ "Anne", "Anna", "Bea", "Bonnie", "Charly", "Claire","Daisy", "Emma", "Fiona", "Fenna", "Gerardi", "Gemma", "Hannah", "Ilse", "Jannie", "Jennie", "Kim", "Karin", "Monica", "Maaike",
					"Megan", "Naomi", "Petra", "Renske", "Simone", "Sjors", "Vera", "Viola" } };
	Random random = new Random();

	public Klant KlantFactory(Cafe cafe, LocalTime tijd) {
		int randomKlant = random.nextInt(10);
		int geslacht = random.nextInt(2);
		String naam = namenlijst[geslacht][random.nextInt(namenlijst[0].length - 1)];
		int aantalBestellingenWens = random.nextInt(5) + 1;
		double geldOpZak = ((double) 100 * (random.nextInt(20) + 1) + (double) random.nextInt(100)) / 100;
		int geduldigheid;

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
}