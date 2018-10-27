package weekopdracht_cafe;

import java.time.LocalTime;

import weekopdracht_cafe.Dranken.Drankje;
import weekopdracht_cafe.Dranken.DrankjeFactory;
import weekopdracht_cafe.Klanten.*;

public class Tijd extends Thread {
	int aantalVerwachteGasten = 2;
	int aantalGenereerPogingen = 0;
	boolean klantAanwezig = false;

	public void run(Cafe cafe, Manager manager) {
		while (aantalGenereerPogingen < 480) {
			try {
				Thread.sleep(100);
				genereerKlanten(cafe);

				if (klantAanwezig) {
					Klant klant = Cafe.klantenlijst.get(cafe.klantenlijst.size() - 1);
					
					Drankje drankje = new DrankjeFactory().getDrankje(klant.drankenWens.get(0));
					while (klant.aanwezig) {
						manager.overweegActies(klant);
						manager.actieNaOverweging(klant, drankje);
					}
				}

			} catch (Exception e) {
				System.out.println("Er ging iets mis.");
			}
			klantAanwezig = false;
		}
	}

	public void genereerKlanten(Cafe cafe) {
		aantalGenereerPogingen++;
		if (Main.random.nextInt(1000) > 959) {
			Klant nieuweklant = new KlantFactory().KlantFactory(getIngameTijd(cafe));
			nieuweklant.binnenkomen(nieuweklant, getIngameTijd(cafe));
			klantAanwezig = true;
		}
	}

	public LocalTime getIngameTijd(Cafe cafe) {
		LocalTime ingameTijd = cafe.getOpeningstijd().plusMinutes(aantalGenereerPogingen);
		return ingameTijd;
	}
}